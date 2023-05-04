package med.vol.api.utils.consultationvalidations;

import med.vol.api.controller.dto.consultation.ConsultationSaveRequestDTO;
import med.vol.api.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class PriorSchedulingValidation implements ConsultationValidations{

    @Override
    public void validate(ConsultationSaveRequestDTO consultationSaveRequestDTO) {
            var now = LocalDateTime.now();
            var dateOfConsultation = consultationSaveRequestDTO.date();
            var difference = Duration.between(now, dateOfConsultation).toMinutes();
            if (difference < 30){
                throw new BadRequestException("Consultations must be scheduled at least 30 minutes in advance!");
            }


    }
}
