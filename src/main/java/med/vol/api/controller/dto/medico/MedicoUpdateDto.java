package med.vol.api.controller.dto.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.vol.api.controller.dto.endereco.DadosEnderecoDto;

public record MedicoUpdateDto(
        @NotNull
        Long id,
        String nome,

        DadosEnderecoDto dadosEnderecoDto) {
}
