package med.vol.api.controller.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEnderecoDto(
        @NotBlank
        String logradouro,
        @NotBlank
        String numero,

        String complemento,
        @NotBlank
        String bairro,
        @NotBlank
        String cidade,
        @NotBlank
        String uf,
        @NotBlank @Pattern(regexp = ("\\d{8}"))
        String cep) {
}
