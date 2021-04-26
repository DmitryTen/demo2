package demo.project.vimpelcom.controllers;

import demo.project.vimpelcom.controllers.models.BasicApiResponse;
import demo.project.vimpelcom.controllers.models.CreateResponse;
import demo.project.vimpelcom.controllers.models.DeleteResponse;
import demo.project.vimpelcom.service.FlushOldRecordsFacade;
import demo.project.vimpelcom.service.RestTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executor;

/**
 *
 */
@RestController
@RequestMapping("api")
public class DemoController {
    private static final Logger log = LoggerFactory.getLogger( DemoController.class );

    private FlushOldRecordsFacade flushOldRecordsFacade;
    private Executor callbackExecutor;
    private RestTemplateService callBackService;


    @Autowired
    public DemoController(FlushOldRecordsFacade flushOldRecordsFacade, Executor callbackExecutor, RestTemplateService callBackService) {
        this.flushOldRecordsFacade = flushOldRecordsFacade;
        this.callbackExecutor = callbackExecutor;
        this.callBackService = callBackService;
    }


    @DeleteMapping(value = "table_a/old_records/{threshold}")
    public ResponseEntity<DeleteResponse<Integer>> deleteFromTableA(
            @PathVariable
            @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime threshold) {
        return DeleteResponse.createResponseEntity(flushOldRecordsFacade.deleteFromTableA(threshold));
    }

    @DeleteMapping(value = "table_b/old_records/{threshold}")
    public ResponseEntity<DeleteResponse<Long>> deleteFromTableB(
            @PathVariable
            @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime threshold) {
        return DeleteResponse.createResponseEntity(flushOldRecordsFacade.deleteFromTableB(threshold));
    }

    @DeleteMapping(value = "table_c/old_records/{threshold}")
    public ResponseEntity<DeleteResponse<Long>> deleteFromTableC(
            @PathVariable
            @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime threshold) {
        return DeleteResponse.createResponseEntity(flushOldRecordsFacade.deleteFromTableC(threshold));
    }



    @DeleteMapping(value = "table_a/old_records/async/{threshold}")
    public ResponseEntity<BasicApiResponse> deleteFromTableAAsync(
            @PathVariable
            @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime threshold,
            @RequestParam String callbackUrl) {
        callbackExecutor.execute(() -> {
            List<Integer> list = flushOldRecordsFacade.deleteFromTableA(threshold);
            callBackService.sendCallback(callbackUrl, new DeleteResponse<>(list, list.size()), Void.class, (nullObj) -> {
                log.info("callback delivered");
            });
        });
        return BasicApiResponse.createResponseEntity();
    }

    @DeleteMapping(value = "table_b/old_records/async/{threshold}")
    public ResponseEntity<BasicApiResponse> deleteFromTableBAsync(
            @PathVariable
            @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime threshold,
            @RequestParam String callbackUrl) {
        callbackExecutor.execute(() -> {
            List<Long> list = flushOldRecordsFacade.deleteFromTableB(threshold);
            callBackService.sendCallback(callbackUrl, new DeleteResponse<>(list, list.size()), Void.class, (nullObj) -> {
                log.info("callback delivered");
            });
        });
        return BasicApiResponse.createResponseEntity();
    }


    @DeleteMapping(value = "table_c/old_records/async/{threshold}")
    public ResponseEntity<BasicApiResponse> deleteFromTableCAsync(
            @PathVariable
            @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime threshold,
            @RequestParam String callbackUrl) {
        callbackExecutor.execute(() -> {
            List<Long> list = flushOldRecordsFacade.deleteFromTableC(threshold);
            callBackService.sendCallback(callbackUrl, new DeleteResponse<>(list, list.size()), Void.class, (nullObj) -> {
                log.info("callback delivered");
            });
        });
        return BasicApiResponse.createResponseEntity();
    }



    @PostMapping(value = "table_a", consumes = {"application/json"})
    public ResponseEntity<CreateResponse<Integer>> createTableARecord(@RequestBody String name) {
        return CreateResponse.createResponseEntity(flushOldRecordsFacade.createTableARecord(name));
    }

    @PostMapping(value = "table_b", consumes = {"application/json"})
    public ResponseEntity<CreateResponse<Long>> createTableBRecord(@RequestBody String name) {
        return CreateResponse.createResponseEntity(flushOldRecordsFacade.createTableBRecord(name));
    }

    @PostMapping(value = "table_c", consumes = {"application/json"})
    public ResponseEntity<CreateResponse<Long>> createTableCRecord(@RequestBody String name) {
        return CreateResponse.createResponseEntity(flushOldRecordsFacade.createTableCRecord(name));
    }
}
