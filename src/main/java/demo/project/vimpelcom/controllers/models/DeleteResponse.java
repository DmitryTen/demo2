package demo.project.vimpelcom.controllers.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class DeleteResponse <T> extends BasicApiResponse {

    @JsonProperty("deleted_ids")
    private List<T> deletedIds;

    @JsonProperty("deleted_cnt")
    private Integer deletedCount;

    public DeleteResponse(List<T> deletedIds, Integer deletedCount) {
        this.deletedIds = deletedIds;
        this.deletedCount = deletedCount;
    }

    public Integer getDeletedCount() {
        return deletedCount;
    }

    public List<T> getDeletedIds() {
        return deletedIds;
    }

    public static <T2> ResponseEntity<DeleteResponse<T2>> createResponseEntity(List<T2> listIds) {
        return ResponseEntity.ok(new DeleteResponse<>(listIds, listIds.size()));
    }

}
