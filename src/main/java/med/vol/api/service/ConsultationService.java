package med.vol.api.service;

import lombok.RequiredArgsConstructor;
import med.vol.api.controller.dto.consultation.ConsultationResponseDTO;
import med.vol.api.controller.dto.consultation.ConsultationSaveRequestDTO;
import med.vol.api.exceptions.BadRequestException;
import med.vol.api.models.Consultation;
import med.vol.api.models.Doctor;
import med.vol.api.models.Patient;
import med.vol.api.repository.ConsultationRepository;
import med.vol.api.repository.DoctorRepository;
import med.vol.api.repository.PatientRepository;
import med.vol.api.utils.consultationvalidations.ConsultationValidations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultationService {
    private final ConsultationRepository consultationRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final List<ConsultationValidations> consultationValidations ;

    public ConsultationResponseDTO toSchedule(ConsultationSaveRequestDTO consultationSaveRequestDTO) {
        consultationValidations.forEach(v -> v.validate(consultationSaveRequestDTO));

        Patient patient = patientRepository.getReferenceById(consultationSaveRequestDTO.patientId());
        Doctor doctor = chooseDoctor(consultationSaveRequestDTO);
        Consultation consultation = new Consultation(doctor, patient, consultationSaveRequestDTO.date());

        return  new ConsultationResponseDTO(consultationRepository.save(consultation));
    }
    private Doctor chooseDoctor(ConsultationSaveRequestDTO consultationSaveRequestDTO) {
        if (consultationSaveRequestDTO.doctorId() != null) {
            return doctorRepository.getReferenceById(consultationSaveRequestDTO.doctorId());
        }
        if (consultationSaveRequestDTO.specialties() == null) {
            throw new BadRequestException("The specialty is mandatory when no physician is chosen!");
        }
        return doctorRepository.chooseRandomDoctor(consultationSaveRequestDTO.specialties(), consultationSaveRequestDTO.date());
    }
}