package med.vol.api.controller.dto.consultation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.vol.api.models.enums.Specialties;

import java.time.LocalDateTime;

public record ConsultationSaveRequestDTO(
        Long doctorId,
        @NotNull
        Long patientId,
        @NotNull
        @Future
        LocalDateTime date,
        Specialties specialties


){

}

