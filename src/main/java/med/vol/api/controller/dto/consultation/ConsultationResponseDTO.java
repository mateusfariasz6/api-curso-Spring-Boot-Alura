package med.vol.api.controller.dto.consultation;

import med.vol.api.models.Consultation;

import java.time.LocalDateTime;

public record ConsultationResponseDTO(
        Long id,
        Long doctorId,
        Long patientId,
        LocalDateTime date
) {
    public ConsultationResponseDTO(Consultation consultation) {
       this(consultation.getId(), consultation.getDoctor().getId(), consultation.getPatient().getId(), consultation.getDate());
    }
}
