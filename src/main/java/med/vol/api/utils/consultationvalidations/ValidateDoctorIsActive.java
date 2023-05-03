package med.vol.api.utils.consultationvalidations;

import lombok.RequiredArgsConstructor;
import med.vol.api.controller.dto.consultation.ConsultationSaveRequestDTO;
import med.vol.api.exceptions.BadRequestException;
import med.vol.api.models.Doctor;
import med.vol.api.repository.DoctorRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class ValidateDoctorIsActive implements ConsultationValidations {
    private final DoctorRepository doctorRepository;

    @Override
    public void validate(ConsultationSaveRequestDTO consultationSaveRequestDTO) {
        if (consultationSaveRequestDTO.doctorId() == null) {
            return;
        }
        Long doctorId = consultationSaveRequestDTO.doctorId();
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        if (doctor.isPresent()) {
            if (!doctor.get().getStatus()) {
                throw new BadRequestException("Doctor is not active!");
            }
        }


    }
}
