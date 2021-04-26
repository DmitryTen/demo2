package demo.project.vimpelcom.controllers;

import demo.project.vimpelcom.controllers.models.DeleteResponse;
import demo.project.vimpelcom.service.FlushOldRecordsFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("api")
public class DemoController {
    private static final Logger log = LoggerFactory.getLogger( DemoController.class );

    private FlushOldRecordsFacade flushOldRecordsFacade;

    @Autowired
    public DemoController(FlushOldRecordsFacade flushOldRecordsFacade) {
        this.flushOldRecordsFacade = flushOldRecordsFacade;
    }


    @DeleteMapping(value = "table_a/old_records/{threshold}")
    public ResponseEntity<DeleteResponse<Integer>> deleteFromTableA(
            @PathVariable
            @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime threshold) {
        return evalToResponse(flushOldRecordsFacade.deleteFromTableA(threshold));
    }

    @DeleteMapping(value = "table_b/old_records/{threshold}")
    public ResponseEntity<DeleteResponse<String>> deleteFromTableB(
            @PathVariable
            @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime threshold) {
        return evalToResponse(flushOldRecordsFacade.deleteFromTableB(threshold));
    }

    @DeleteMapping(value = "table_c/old_records/{threshold}")
    public ResponseEntity<DeleteResponse<Long>> deleteFromTableC(
            @PathVariable
            @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime threshold) {
        return evalToResponse(flushOldRecordsFacade.deleteFromTableC(threshold));
    }

    private <T> ResponseEntity<DeleteResponse<T>> evalToResponse(List<T> listIds) {
        return ResponseEntity.ok(new DeleteResponse<>(listIds, listIds.size()));
    }
}
