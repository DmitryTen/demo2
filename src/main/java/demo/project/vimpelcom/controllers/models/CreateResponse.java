package demo.project.vimpelcom.controllers.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.ResponseEntity;

public class CreateResponse <T> extends BasicApiResponse {

    @JsonProperty("created_id")
    private T idValue;

    public CreateResponse(T idValue) {
        this.idValue = idValue;
    }


    public T getIdValue() {
        return idValue;
    }



    public static <T2> ResponseEntity<CreateResponse<T2>> createResponseEntity(T2 idValue) {
        return ResponseEntity.ok(new CreateResponse<>(idValue));
    }

}
