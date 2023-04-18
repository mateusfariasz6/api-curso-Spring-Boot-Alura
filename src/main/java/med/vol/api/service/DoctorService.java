package med.vol.api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import med.vol.api.controller.dto.doctor.DoctorSaveRequestDTO;
import med.vol.api.controller.dto.doctor.DoctorResponseDTO;
import med.vol.api.controller.dto.doctor.DoctorUpdateRequestDTO;
import med.vol.api.models.Address;
import med.vol.api.models.Doctor;
import med.vol.api.repository.DoctorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public Page<DoctorResponseDTO> listAllByStatusIsTrue(Pageable pageable){
        return doctorRepository.findAllByStatusIsTrue(pageable).map(DoctorResponseDTO::new);
    }

    public DoctorResponseDTO findById(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow();
        if (doctor.getStatus() == Boolean.FALSE){
            throw new RuntimeException("Medico não encontrado.");
        }
        return new DoctorResponseDTO(doctor);

    }
    @Transactional
    public void save(DoctorSaveRequestDTO doctorSaveRequestDto){
        doctorRepository.save(new Doctor(doctorSaveRequestDto));
    }
    @Transactional
    public void update(Long id, DoctorUpdateRequestDTO doctorUpdateRequestDTO){
        Doctor doctor = doctorRepository.findById(id).orElseThrow();
        doctor.setName(doctorUpdateRequestDTO.name());
        doctor.setAddress(new Address(doctorUpdateRequestDTO.address()));
        doctorRepository.save(doctor);
    }

    @Transactional
    public void changeStatus(Long id){
         Doctor doctor = doctorRepository.findById(id).orElseThrow();
         doctor.setStatus(false);
         doctorRepository.save(doctor);
    }


}
