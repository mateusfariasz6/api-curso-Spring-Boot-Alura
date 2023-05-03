package med.vol.api.repository;

import med.vol.api.models.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    @Query(value = """
            select c from Consultation c
            where
            c.patient_id = :patientId
            and
            c.date between :firstTime and endTime;
            
            """)
    boolean existsByPatientIdAndDateBetween(Long patientId, LocalDateTime fistTime, LocalDateTime endTime);

    @Query(value = """
            select c from Consultation c
            where
            c.doctor_id = :doctorId
            and
            c.date = :date
            """)
    boolean existsByDoctorIdAndDate(Long doctorId, LocalDateTime date);
}
