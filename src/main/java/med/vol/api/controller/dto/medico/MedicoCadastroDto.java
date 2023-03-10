package med.vol.api.controller.dto.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.vol.api.controller.dto.endereco.DadosEnderecoDto;
import med.vol.api.models.Endereco;
import med.vol.api.models.enums.Especialidade;

public record MedicoCadastroDto(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull @Valid
        DadosEnderecoDto endereco){
}
