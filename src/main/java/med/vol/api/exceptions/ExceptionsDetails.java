package med.vol.api.exceptions;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
public class ExceptionsDetails {
    protected String title;
    protected int status;
    protected String details;
    protected LocalDateTime timestamp;
}
