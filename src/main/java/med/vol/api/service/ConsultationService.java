package med.vol.api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import med.vol.api.controller.dto.consultation.ConsultationResponseDTO;
import med.vol.api.controller.dto.consultation.ConsultationSaveRequestDTO;
import med.vol.api.exceptions.BadRequestException;
import med.vol.api.exceptions.ResourceNotFundException;
import med.vol.api.models.Consultation;
import med.vol.api.models.Doctor;
import med.vol.api.models.Patient;
import med.vol.api.repository.ConsultationRepository;
import med.vol.api.repository.DoctorRepository;
import med.vol.api.repository.PatientRepository;
import med.vol.api.utils.consultationvalidations.ConsultationValidations;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultationService {
    private final ConsultationRepository consultationRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final List<ConsultationValidations> consultationValidations ;

    @Transactional
    public ConsultationResponseDTO toSchedule(ConsultationSaveRequestDTO consultationSaveRequestDTO) {
        consultationValidations.forEach(v -> v.validate(consultationSaveRequestDTO));

        Patient patient = patientRepository.getReferenceById(consultationSaveRequestDTO.patientId());
        Doctor doctor = chooseDoctor(consultationSaveRequestDTO);
        Consultation consultation = new Consultation(doctor, patient, consultationSaveRequestDTO.date());

        return  new ConsultationResponseDTO(consultationRepository.save(consultation));
    }
    public List<ConsultationResponseDTO> listAll(Pageable pageable) {
        return consultationRepository.findAll(pageable).map(ConsultationResponseDTO::new).getContent();
    }

    public List<ConsultationResponseDTO> listAllByDoctorId(Long id, Pageable pageable) {
       boolean doctorExist = consultationRepository.existsByDoctorId(id);
       if (!doctorExist){
           throw new ResourceNotFundException("doctor does not exist, or you do not have an appointment with that doctor!");
       }
        return consultationRepository.findAllByDoctorId(id, pageable);
    }

    public List<ConsultationResponseDTO> listAllByPatientId(Long id, Pageable pageable) {
        boolean patientExist = consultationRepository.existsByPatientId(id);
        if (!patientExist){
            throw new ResourceNotFundException("patient does not exist, or no appointment is scheduled for this patient!");
        }
        return consultationRepository.findAllByPatientId(id, pageable);
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