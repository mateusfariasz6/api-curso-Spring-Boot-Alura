package med.vol.api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import med.vol.api.controller.dto.patient.PatientResponseDTO;
import med.vol.api.controller.dto.patient.PatientSaveDTO;
import med.vol.api.models.Patient;
import med.vol.api.repository.PatientRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public List<PatientResponseDTO> listAll(Pageable pageable){
        return patientRepository.findAll().stream().map(PatientResponseDTO::new).toList();
    }

    public PatientResponseDTO findById(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow();
        return new PatientResponseDTO(patient);
    }

    @Transactional
    public void save(PatientSaveDTO patientSaveDTO){
        patientRepository.save(new Patient(patientSaveDTO));
    }
}
