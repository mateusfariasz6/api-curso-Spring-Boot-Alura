package med.vol.api.controller.dto.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.vol.api.controller.dto.address.AddressDTO;
import med.vol.api.models.enums.Specialties;

public record DoctorSaveRequestDTO(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Specialties specialties,
        @NotNull @Valid
        AddressDTO address){
}
