package med.vol.api.controller.dto.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.vol.api.controller.dto.address.AddressDTO;

public record PatientSaveRequestDTO(
        @NotBlank @Size(min = 3, max = 300)
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank @Pattern(regexp = "^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$")
        String cpf,
        @NotBlank @Pattern(regexp = "\\d{11}")
        String telephone,
        @NotNull @Valid
        AddressDTO address) {
}
