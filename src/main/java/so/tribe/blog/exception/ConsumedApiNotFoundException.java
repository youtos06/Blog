package so.tribe.blog.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ConsumedApiNotFoundException extends RuntimeException{
    private static final Logger log = LoggerFactory.getLogger(ConsumedApiNotFoundException.class);

    public ConsumedApiNotFoundException(String message){
        super(message);
        log.error(message);
    }
}
