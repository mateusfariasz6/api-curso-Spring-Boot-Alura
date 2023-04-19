package med.vol.api.models;

import jakarta.persistence.*;
import lombok.*;
import med.vol.api.controller.dto.doctor.DoctorSaveRequestDTO;
import med.vol.api.controller.dto.doctor.DoctorUpdateRequestDTO;
import med.vol.api.models.enums.Specialties;

import java.util.InputMismatchException;

@Table(name = "doctor")
@Entity(name = "Doctor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "crm", unique = true)
    private String crm;
    @Enumerated(EnumType.STRING)
    @Column(name = "specialties")
    private Specialties specialties;
    @Embedded
    private Address address;
    private Boolean status;


    public Doctor(DoctorSaveRequestDTO doctorSaveRequestDto) {
        this.name = doctorSaveRequestDto.name();
        this.email = doctorSaveRequestDto.email();
        this.crm = doctorSaveRequestDto.crm();
        this.address = new Address(doctorSaveRequestDto.address());
        this.specialties = doctorSaveRequestDto.specialties();
        this.status = true;
    }

    public void updateData(DoctorUpdateRequestDTO doctorUpdateRequestDTO) {
        if (doctorUpdateRequestDTO.name() != null) {
            if (doctorUpdateRequestDTO.name().matches("^[a-zA-Z ]{1,60}$")) {
                this.name = doctorUpdateRequestDTO.name();
            } else {

                throw new InputMismatchException("O campo não atende os requisitos");

            }
        }
        if (doctorUpdateRequestDTO.address() != null) {
            this.address.updateData(doctorUpdateRequestDTO.address());
        }
    }
}
