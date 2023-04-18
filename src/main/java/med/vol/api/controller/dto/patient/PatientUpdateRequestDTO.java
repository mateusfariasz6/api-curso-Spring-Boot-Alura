package med.vol.api.controller.dto.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import med.vol.api.controller.dto.address.AddressDTO;

public record PatientUpdateRequestDTO(
        @NotBlank @Size(min = 3, max = 300)
        String name,
        @NotBlank @Pattern(regexp = "\\d{11}")
        String telephone,
        @NotNull @Valid
        AddressDTO address) {
}
