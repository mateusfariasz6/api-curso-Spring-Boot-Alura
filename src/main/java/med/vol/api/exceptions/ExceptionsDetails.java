package med.vol.api.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExceptionsDetails {
    private String title;
    private int status;
    private String details;
    private LocalDateTime timestamp;
}
