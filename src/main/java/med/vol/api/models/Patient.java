package med.vol.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.vol.api.controller.dto.patient.PatientSaveRequestDTO;
import med.vol.api.controller.dto.patient.PatientUpdateRequestDTO;

import java.util.InputMismatchException;

@Entity
@Table(name = "patients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Patient {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;

    private String telephone;
    @Column(unique = true)
    private String cpf;
    @Embedded
    private Address address;
    private Boolean status = true;

    public Patient(PatientSaveRequestDTO patientSaveRequestDTO) {
        this.cpf = patientSaveRequestDTO.cpf();
        this.email = patientSaveRequestDTO.email();
        this.name = patientSaveRequestDTO.name();
        this.telephone = patientSaveRequestDTO.telephone();
        this.address = new Address(patientSaveRequestDTO.address());
    }

    public void updateData(PatientUpdateRequestDTO patientUpdateRequestDTO) {

        if (patientUpdateRequestDTO.name() != null) {
            if (patientUpdateRequestDTO.name().matches("^[a-zA-Z ]{1,60}$")) {
                this.name = patientUpdateRequestDTO.name();
            } else {
                throw new InputMismatchException("O campo não atende os requisitos");
            }
        }
        if (patientUpdateRequestDTO.telephone() != null) {
            if (patientUpdateRequestDTO.telephone().matches("^\\d{11}")) {
                this.telephone = patientUpdateRequestDTO.telephone();
            } else {
                throw new InputMismatchException("O campo não atende os requisitos");
            }
        }
        if (patientUpdateRequestDTO.address() != null) {
            this.address.updateData(patientUpdateRequestDTO.address());
        }
    }
}
