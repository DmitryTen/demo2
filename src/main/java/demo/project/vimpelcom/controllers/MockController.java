package demo.project.vimpelcom.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@RestController
@RequestMapping("mock")
public class MockController {
    private static final Logger log = LoggerFactory.getLogger( MockController.class );



    @PostMapping(value = "callbackMock", consumes = {"application/json"})
    public ResponseEntity createTableARecord(@RequestBody String body) {

        log.info("CALLBACK: {}", body);

        return ResponseEntity.ok().build();
    }

}
