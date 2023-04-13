package med.vol.api.controller.dto.medico;

import jakarta.validation.constraints.NotNull;
import med.vol.api.controller.dto.endereco.AddressDto;
import med.vol.api.models.enums.Especialidade;

public record MedicoUpdateDto(
        @NotNull
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade,
        AddressDto dadosEnderecoDto) {
}
