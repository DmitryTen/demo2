package demo.project.vimpelcom.controllers.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DeleteResponse <T> extends AbstractApiResponse{

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

}
