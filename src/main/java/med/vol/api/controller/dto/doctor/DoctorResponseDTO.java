package med.vol.api.controller.dto.medico;

import med.vol.api.models.Medico;
import med.vol.api.models.enums.Especialidade;

public record MedicoListagemDto(Long id,String nome, String email, String crm, Especialidade especialidade) {

    public MedicoListagemDto(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
