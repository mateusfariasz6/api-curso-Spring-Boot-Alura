package med.vol.api.handler;

import med.vol.api.exceptions.BadRequestException;
import med.vol.api.exceptions.ExceptionsDetails;
import med.vol.api.exceptions.ResourceNotFundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionsDetails> handlerResourceNotFundException(ResourceNotFundException ex) {
        return new ResponseEntity<>(
                ExceptionsDetails.builder()
                        .title("Bad Request Exception, Check The Documentation.")
                        .status(HttpStatus.NOT_FOUND.value())
                        .timestamp(LocalDateTime.now())
                        .details(ex.getMessage())
                        .build(), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionsDetails> handlerBadRequest(BadRequestException ex) {
        return new ResponseEntity<>(
                ExceptionsDetails.builder()
                        .title("Bad Request Exception, Check The Documentation.")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .details(ex.getMessage())
                        .build(), HttpStatus.BAD_REQUEST
        );
    }

}
