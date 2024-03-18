package vn.smartshop.server.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.smartshop.server.model.dto.response.AuthException;
import vn.smartshop.server.model.dto.response.Response;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = {AuthException.class})
    public ResponseEntity<Response> resourceNotFoundException(AuthException ex) {
        if (ex.getError()==401){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Response.builder().status(ex.getError()).message("UNAUTHORIZED").build());
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Response.builder().status(ex.getError()).message("FORBIDDEN").build());
    }
}
