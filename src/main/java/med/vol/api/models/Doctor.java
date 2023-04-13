package med.vol.api.models;

import jakarta.persistence.*;
import lombok.*;
import med.vol.api.controller.dto.medico.MedicoCadastroDto;
import med.vol.api.models.enums.Especialidade;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String nome;
    @Column(name = "email")
    private String email;
    @Column(name = "crm")
    private String crm;
    @Enumerated(EnumType.STRING)
    @Column(name = "")
    private Especialidade especialidade;
    @Embedded
    private Address endereco;


    public Medico(MedicoCadastroDto medicoCadastroDto) {
        this.nome = medicoCadastroDto.nome();
        this.email = medicoCadastroDto.email();
        this.crm = medicoCadastroDto.crm();
        this.endereco = new Address(medicoCadastroDto.endereco());
        this.especialidade = medicoCadastroDto.especialidade();
    }
}
