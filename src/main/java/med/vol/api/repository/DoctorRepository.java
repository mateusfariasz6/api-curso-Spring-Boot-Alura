package med.vol.api.repository;

import med.vol.api.models.Doctor;
import med.vol.api.models.enums.Specialties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByStatusIsTrue(Pageable pageable);

    @Query(value = """
            select d from Doctor d
            where
            d.status = 1
            and
            d.specialties = :specialties
            and
            d.id not in(
            
                select c.doctor from Consultation c
                where
                c.date = :date
            
            )
            order by rand()
            limit 1
            
            
            """)
    Doctor chooseRandomDoctor(Specialties specialties, LocalDateTime date);

    boolean existsByIdAndStatusIsTrue(Long id);
}