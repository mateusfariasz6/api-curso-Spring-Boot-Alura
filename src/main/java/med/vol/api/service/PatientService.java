package med.vol.api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import med.vol.api.controller.dto.patient.PatientResponseDTO;
import med.vol.api.controller.dto.patient.PatientSaveRequestDTO;
import med.vol.api.controller.dto.patient.PatientUpdateRequestDTO;
import med.vol.api.models.Address;
import med.vol.api.models.Patient;
import med.vol.api.repository.PatientRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public List<PatientResponseDTO> listAll(Pageable pageable){
        return patientRepository.findAllByStatusIsTrue(pageable).stream().map(PatientResponseDTO::new).toList();
    }

    public PatientResponseDTO findById(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow();
        if (!patient.getStatus()){
            throw new NoSuchElementException("Paciente não encontrado.");
        }
        return new PatientResponseDTO(patient);
    }

    @Transactional
    public void save(PatientSaveRequestDTO patientSaveRequestDTO){
        patientRepository.save(new Patient(patientSaveRequestDTO));
    }

    @Transactional
    public PatientResponseDTO update(Long id, PatientUpdateRequestDTO patientUpdateRequestDTO){

        Patient patient = patientRepository.findById(id).orElseThrow();
        if (!patient.getStatus()){
            throw new NoSuchElementException("Paciente não encontrado");
        }
        patient.setName(patientUpdateRequestDTO.name());
        patient.setTelephone(patientUpdateRequestDTO.telephone());
        patient.setAddress(new Address(patientUpdateRequestDTO.address()));
        return new PatientResponseDTO(patientRepository.save(patient));
    }

    public void delete(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow();
        if (!patient.getStatus()){
            throw new NoSuchElementException("Paciente não encontrado!");
        }
        patient.setStatus(false);
        patientRepository.save(patient);

    }


}
