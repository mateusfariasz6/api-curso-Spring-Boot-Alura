package med.vol.api.repository;

import med.vol.api.models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findAllByStatusIsTrue(Pageable pageable);

    boolean existsByIdAndStatusIsTrue(Long id);
}
