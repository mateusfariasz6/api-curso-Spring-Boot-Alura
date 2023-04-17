package med.vol.api.controller.dto.patient;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import med.vol.api.controller.dto.address.AddressDTO;

public record PatientSaveDTO(
        @NotBlank @Size(min = 3, max = 300)
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank @Pattern(regexp = "^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$")
        String cpf,
        @NotBlank @Pattern(regexp = "\\d{11}")
        String telephone,
        AddressDTO address) {
}
