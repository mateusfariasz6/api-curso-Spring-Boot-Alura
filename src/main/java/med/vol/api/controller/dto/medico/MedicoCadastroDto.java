package med.vol.api.controller.dto.medico;

import med.vol.api.models.Endereco;
import med.vol.api.models.enums.Especialidade;

public record MedicoCadastroDto(String nome, String email,String crm, Especialidade especialidade, Endereco endereco){
}
