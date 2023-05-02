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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsultationService {
    private final ConsultationRepository consultationRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    public ConsultationResponseDTO toSchedule(ConsultationSaveRequestDTO consultationSaveRequestDTO){
        Optional<Doctor> doctor;
        Optional<Patient> patient = patientRepository.findById(consultationSaveRequestDTO.patientId());
        if (patient.isEmpty() || !patient.get().getStatus()){
            throw new BadRequestException("Patient not found!");
        }
        if (consultationSaveRequestDTO.doctorId() != null){
            doctor = doctorRepository.findById(consultationSaveRequestDTO.doctorId());
            if (doctor.isEmpty() || !doctor.get().getStatus()){
                throw new BadRequestException("Doctor not found!");
            }
        }

        return null;
    }
}
