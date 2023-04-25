package med.vol.api.controller.dto.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DataForUserAuthenticationRequestDTO(
        @NotBlank @Email
        String login,
        @NotBlank
        String password) {
}
