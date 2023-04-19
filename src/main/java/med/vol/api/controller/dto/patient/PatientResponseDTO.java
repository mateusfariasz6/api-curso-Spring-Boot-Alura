package med.vol.api.controller.dto.patient;

import med.vol.api.models.Patient;

public record PatientResponseDTO(Long id, String name, String email, String cpf) {
    public PatientResponseDTO(Patient patient){
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
