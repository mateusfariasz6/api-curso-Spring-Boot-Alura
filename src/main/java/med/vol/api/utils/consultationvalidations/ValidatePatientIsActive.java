package med.vol.api.utils.consultationvalidations;

import lombok.RequiredArgsConstructor;
import med.vol.api.controller.dto.consultation.ConsultationSaveRequestDTO;
import med.vol.api.exceptions.BadRequestException;
import med.vol.api.models.Patient;
import med.vol.api.repository.PatientRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ValidatePatientIsActive implements ConsultationValidations{
    private final PatientRepository patientRepository;
    @Override
    public void validate(ConsultationSaveRequestDTO consultationSaveRequestDTO) {

        boolean patientActive = patientRepository.existsByIdAndStatusIsTrue(consultationSaveRequestDTO.patientId());
        if (!patientActive){
            throw new BadRequestException("Patient does not exist or is not active!");
        }
    }
}
