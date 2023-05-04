package med.vol.api.utils.consultationvalidations;

import lombok.RequiredArgsConstructor;
import med.vol.api.controller.dto.consultation.ConsultationSaveRequestDTO;
import med.vol.api.exceptions.BadRequestException;
import med.vol.api.models.Doctor;
import med.vol.api.repository.DoctorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ValidateDoctorIsActive implements ConsultationValidations {
    private final DoctorRepository doctorRepository;

    @Override
    public void validate(ConsultationSaveRequestDTO consultationSaveRequestDTO) {
        if (consultationSaveRequestDTO.doctorId() == null) {
            return;
        }
        boolean doctorIsActive = doctorRepository.existsByIdAndStatusIsTrue(consultationSaveRequestDTO.doctorId());
        if (!doctorIsActive){
            throw new BadRequestException("Doctor not found or inactive!");
        }

    }
}
