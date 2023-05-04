package med.vol.api.utils.consultationvalidations;

import lombok.RequiredArgsConstructor;
import med.vol.api.controller.dto.consultation.ConsultationSaveRequestDTO;
import med.vol.api.exceptions.BadRequestException;
import med.vol.api.repository.ConsultationRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class ValidatePatientAlreadyHasConsultationOnTheDay implements ConsultationValidations {
    private final ConsultationRepository consultationRepository;
    public void validate(ConsultationSaveRequestDTO consultationSaveRequestDTO) {
        Long patientId = consultationSaveRequestDTO.patientId();
        LocalDateTime firstTime = consultationSaveRequestDTO.date().withHour(7);
        LocalDateTime endTime = consultationSaveRequestDTO.date().withHour(18);

        boolean existConsultationForThePatient = consultationRepository.existsByPatientIdAndDateBetween(patientId,firstTime, endTime);

        if (existConsultationForThePatient){
            throw new BadRequestException("The patient already has an appointment on the informed day!");
        }
    }
}
