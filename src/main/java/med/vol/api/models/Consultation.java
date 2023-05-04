package med.vol.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.vol.api.controller.dto.consultation.ConsultationSaveRequestDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "consultation")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private LocalDateTime date;

    public Consultation(Doctor doctor, Patient patient, LocalDateTime date) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
    }
}
