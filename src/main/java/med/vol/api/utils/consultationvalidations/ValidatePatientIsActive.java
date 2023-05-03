package med.vol.api.utils.consultationvalidations;

import lombok.RequiredArgsConstructor;
import med.vol.api.controller.dto.consultation.ConsultationSaveRequestDTO;
import med.vol.api.exceptions.BadRequestException;
import med.vol.api.models.Patient;
import med.vol.api.repository.PatientRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class ValidatePatientIsActive implements ConsultationValidations{
    private final PatientRepository patientRepository;
    @Override
    public void validate(ConsultationSaveRequestDTO consultationSaveRequestDTO) {
        Optional<Patient> patient = patientRepository.findById(consultationSaveRequestDTO.patientId());
        if (patient.isPresent()){
            if (!patient.get().getStatus()){
                throw new BadRequestException("Patient not is active!");
            }
        }
    }
}
