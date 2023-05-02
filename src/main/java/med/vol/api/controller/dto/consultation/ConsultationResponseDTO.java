package med.vol.api.controller.dto.consultation;

import java.time.LocalDateTime;

public record ConsultationResponseDTO(
        Long id,
        Long doctorId,
        Long patientId,
        LocalDateTime date
) {
}
