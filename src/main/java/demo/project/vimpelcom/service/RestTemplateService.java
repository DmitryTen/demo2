package demo.project.vimpelcom.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.function.Consumer;

@Service
public class RestTemplateService {

    private static final Logger log = LoggerFactory.getLogger( RestTemplateService.class );


    private final RestTemplate restTemplate;

    @Autowired
    public RestTemplateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public <REQ, RESP> void sendCallback(String url, REQ object, Class<RESP> respClass, Consumer<RESP> consumer){
        HttpEntity<REQ> entity = generateRequestWithDefaultHeaders(object);
        try {
            if (respClass == Void.class) {
                ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);
                consumer.accept(null);
            } else {
                ResponseEntity<RESP> responseEntity = restTemplate.postForEntity(url, entity, respClass);
                consumer.accept(responseEntity.getBody());
            }
        }catch (Exception e) {
            log.info("Exception", e);
        }
    }



    private  <T> HttpEntity<T> generateRequestWithDefaultHeaders(T requestBody) {
        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-type", "application/json");

        if(requestBody != null){
            return new HttpEntity<T>(requestBody, headers);
        }
        return new HttpEntity<>(headers);
    }
}
