package med.vol.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.vol.api.controller.dto.patient.PatientSaveDTO;

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

   public Patient(PatientSaveDTO patientSaveDTO){
        this.cpf = patientSaveDTO.cpf();
        this.email = patientSaveDTO.email();
        this.name = patientSaveDTO.name();
        this.telephone = patientSaveDTO.telephone();
        this.address = new Address(patientSaveDTO.address());
    }

}
