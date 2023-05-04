package med.vol.api.handler;

import med.vol.api.exceptions.BadRequestException;
import med.vol.api.exceptions.ExceptionsDetails;
import med.vol.api.exceptions.ResourceNotFundException;
import med.vol.api.exceptions.ValidationExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionsDetails> handlerResourceNotFundException(ResourceNotFundException ex) {
        return new ResponseEntity<>(
                ExceptionsDetails.builder()
                        .title("Resource not fund exception, Check The Documentation.")
                        .status(HttpStatus.NOT_FOUND.value())
                        .timestamp(LocalDateTime.now())
                        .details(ex.getMessage())
                        .build(), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationExceptionDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        String fields = errors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = errors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
        return new ResponseEntity<>(ValidationExceptionDetails.builder()
                .title("Bad Request exception, Check the Documentation")
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .details("Fields errors")
                .fields(fields)
                .fieldsMessage(fieldsMessage)
                .build(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionsDetails> handleBadRequestException(BadRequestException ex){

        return new ResponseEntity<>(
                ExceptionsDetails.builder()
                        .title("Bad request exception, Check The Documentation.")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .details(ex.getMessage())
                        .build(), HttpStatus.BAD_REQUEST
        );

    }


}
