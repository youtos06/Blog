package so.tribe.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Collections;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
        String type = ex.getRequiredType().getSimpleName();
        Object value = ex.getValue();
        String message = String.format("%s parameter is invalid",type);
        return new ResponseEntity(Collections.singletonMap("error",message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        String message = String.format("%s parameter is required", name);
        return new ResponseEntity(Collections.singletonMap("error",message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<Object> handleInvalidParameterException(InvalidParameterException ex) {
        String name = ex.getParameterName();
        String message = String.format("%s parameter is invalid", name);
        return new ResponseEntity(Collections.singletonMap("error", message), HttpStatus.BAD_REQUEST);
    }
}
