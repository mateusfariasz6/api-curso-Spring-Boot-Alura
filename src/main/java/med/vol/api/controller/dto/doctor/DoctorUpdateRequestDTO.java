package med.vol.api.controller.dto.doctor;

import jakarta.validation.constraints.NotNull;
import med.vol.api.controller.dto.address.AddressDTO;
import med.vol.api.models.enums.Specialties;

public record DoctorUpdateRequestDTO(
        String name,
        AddressDTO address) {
}
