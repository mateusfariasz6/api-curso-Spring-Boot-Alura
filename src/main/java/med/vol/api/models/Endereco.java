package med.vol.api.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public Endereco(Endereco endereco) {
        this.logradouro = endereco.logradouro;
        this.numero = endereco.numero;
        this.complemento = endereco.complemento;
        this.cidade = endereco.cidade;
        this.bairro = endereco.bairro;
        this.cep = endereco.cep;
        this.uf = endereco.uf;

    }
}
