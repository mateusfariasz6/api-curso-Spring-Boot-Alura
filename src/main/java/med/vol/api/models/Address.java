package med.vol.api.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.controller.dto.address.AddressDTO;

import java.util.InputMismatchException;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String uf;
    private String cep;

    public Address(AddressDTO addressDTO) {
        this.street = addressDTO.street();
        this.number = addressDTO.number();
        this.complement = addressDTO.complement();
        this.city = addressDTO.city();
        this.neighborhood = addressDTO.neighborhood();
        this.cep = addressDTO.cep();
        this.uf = addressDTO.uf();

    }

    public void updateData(AddressDTO address) {
        if (address.street() != null) {
            if (address.street().matches("^[a-zA-Z0-9 ]{1,200}$")) {
                this.street = address.street();
            } else {
                throw new InputMismatchException("O campo não atende os requisitos");
            }
        }
        if (address.cep() != null) {
            if (address.cep().matches("^[0-9]{8}$")) {
                this.cep = address.cep();
            } else {
                throw new InputMismatchException("O campo não atende os requisitos");
            }
        }
        if (address.city() != null) {
            if (address.city().matches("^[a-zA-Z0-9 ]{1,200}$")) {
                this.street = address.city();
            } else {
                throw new InputMismatchException("O campo não atende os requisitos");
            }
        }
        if (address.neighborhood() != null) {
            if (address.neighborhood().matches("^[a-zA-Z0-9 ]{1,200}$")) {
                this.street = address.neighborhood();
            } else {
                throw new InputMismatchException("O campo não atende os requisitos");
            }
        }
        if (address.number() != null) {
            if (address.number().matches("^\\d+$")) {

                this.number = address.number();
            } else {
                throw new InputMismatchException("O campo não atende os requisitos");
            }
        }
        if (address.complement() != null) {
            if (address.complement().matches("^[a-zA-Z0-9 ]{1,600}$")){
                this.complement = address.complement();
            }else {
                throw new InputMismatchException("O campo não atende os requisitos");
            }
        }
        if (address.uf() != null) {
            if (address.uf().matches("^[a-zA-Z]{2}")){
                this.neighborhood = address.uf();
            }else {
                throw new InputMismatchException("O campo não atende os requisitos");
            }
        }

    }
}
