package med.vol.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.controller.dto.medico.MedicoCadastroDto;
import med.vol.api.models.enums.Especialidade;
@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class Medico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
   @Embedded
    private Endereco endereco;


    public Medico(MedicoCadastroDto medicoCadastroDto) {
        this.nome = medicoCadastroDto.nome();
        this.email = medicoCadastroDto.email();
        this.crm = medicoCadastroDto.crm();
        this.endereco = new Endereco(medicoCadastroDto.endereco());
        this.especialidade = medicoCadastroDto.especialidade();
    }
}
