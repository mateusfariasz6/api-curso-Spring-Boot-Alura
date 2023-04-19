package med.vol.api.controller.dto.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import med.vol.api.controller.dto.address.AddressDTO;
import med.vol.api.models.enums.Specialties;

public record DoctorUpdateRequestDTO(
        String name,
        AddressDTO address) {
}
