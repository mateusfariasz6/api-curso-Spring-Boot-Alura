package med.vol.api.controller.dto.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressDTO(
        @NotBlank
        String street,
        @NotBlank
        String number,
        String complement,
        @NotBlank
        String neighborhood,
        @NotBlank
        String city,
        @NotBlank @Size(min = 2)
        String uf,
        @NotBlank @Pattern(regexp = ("\\d{8}"))
        String cep) {
}
