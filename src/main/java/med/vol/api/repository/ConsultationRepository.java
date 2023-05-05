package med.vol.api.repository;

import med.vol.api.controller.dto.consultation.ConsultationResponseDTO;
import med.vol.api.models.Consultation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    //    @Query(value = """
//            select c from Consultation c
//            where
//            c.patient = :patientId
//            and
//            c.date between :firstTime and :endTime
//
//            """)
    boolean existsByPatientIdAndDateBetween(Long patientId, LocalDateTime firstTime, LocalDateTime endTime);

    //    @Query(value = """
//            select c from Consultation c
//            where
//            c.doctor = :doctorId
//            and
//            c.date = :date
//            """)
    boolean existsByDoctorIdAndDate(Long doctorId, LocalDateTime date);

    List<ConsultationResponseDTO> findAllByDoctorId(Long id, Pageable pageable);

    boolean existsByDoctorId(Long doctorId);

    boolean existsByPatientId(Long patientId);

    List<ConsultationResponseDTO> findAllByPatientId(Long patientId, Pageable pageable);
}
