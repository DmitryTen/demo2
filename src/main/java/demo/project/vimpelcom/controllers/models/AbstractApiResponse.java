package demo.project.vimpelcom.controllers.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public abstract class AbstractApiResponse {

    @JsonProperty("response_date_time")
    private LocalDateTime responseDateTime = LocalDateTime.now();

    public LocalDateTime getResponseDateTime() {
        return responseDateTime;
    }
}
