package med.vol.api.service;

import lombok.RequiredArgsConstructor;
import med.vol.api.controller.dto.consultation.ConsultationResponseDTO;
import med.vol.api.controller.dto.consultation.ConsultationSaveRequestDTO;
import med.vol.api.exceptions.BadRequestException;
import med.vol.api.models.Doctor;
import med.vol.api.models.Patient;
import med.vol.api.repository.ConsultationRepository;
import med.vol.api.repository.DoctorRepository;
import med.vol.api.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsultationService {
    private final ConsultationRepository consultationRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public ConsultationResponseDTO toSchedule(ConsultationSaveRequestDTO consultationSaveRequestDTO) {
        Patient patient = patientRepository.findById(consultationSaveRequestDTO.patientId()).orElseThrow(() -> new BadRequestException("Patient not found!"));
        if (consultationSaveRequestDTO.doctorId() != null && !doctorRepository.existsById(consultationSaveRequestDTO.doctorId())) {
            throw new BadRequestException("Doctor not found!");
        }
        Doctor doctor = chooseDoctor(consultationSaveRequestDTO);


        return null;
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
