package demo.project.vimpelcom.controllers.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public class BasicApiResponse {

    @JsonProperty("response_date_time")
    private LocalDateTime responseDateTime = LocalDateTime.now();

    public LocalDateTime getResponseDateTime() {
        return responseDateTime;
    }



    public static  ResponseEntity<BasicApiResponse> createResponseEntity() {
        return ResponseEntity.ok(new BasicApiResponse());
    }
}
