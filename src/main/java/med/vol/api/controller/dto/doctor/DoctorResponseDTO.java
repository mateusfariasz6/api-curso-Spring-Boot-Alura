package med.vol.api.controller.dto.doctor;

import med.vol.api.models.Doctor;
import med.vol.api.models.enums.Specialties;

public record DoctorResponseDTO(Long id, String name, String email, String crm, Specialties specialties) {

    public DoctorResponseDTO(Doctor medico){
        this(medico.getId(), medico.getName(), medico.getEmail(), medico.getCrm(), medico.getSpecialties());
    }
}
