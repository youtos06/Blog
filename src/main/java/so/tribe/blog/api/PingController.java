package so.tribe.blog.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import so.tribe.blog.configuration.ApiPath;

import java.util.HashMap;

@RestController
@RequestMapping(value = ApiPath.LEVEL + ApiPath.PING)
public class PingController {

    private static final Logger log = LoggerFactory.getLogger(PingController.class);
    @GetMapping("")
    public ResponseEntity<?> getPing(){
        HashMap<String,Boolean> result = new HashMap<>();
        result.put("success",true);
        log.info("Calling "+ ApiPath.LEVEL + ApiPath.PING);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
