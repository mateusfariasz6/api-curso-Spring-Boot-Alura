package med.vol.api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import med.vol.api.controller.dto.doctor.DoctorResponseDTO;
import med.vol.api.controller.dto.doctor.DoctorSaveRequestDTO;
import med.vol.api.controller.dto.doctor.DoctorUpdateRequestDTO;
import med.vol.api.exceptions.BadRequestException;
import med.vol.api.exceptions.ResourceNotFundException;
import med.vol.api.models.Doctor;
import med.vol.api.repository.DoctorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public Page<DoctorResponseDTO> listAllByStatusIsTrue(Pageable pageable) {
        return doctorRepository.findAllByStatusIsTrue(pageable).map(DoctorResponseDTO::new);
    }

    public DoctorResponseDTO findById(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(BadRequestException::new);
        if (!doctor.getStatus()) {
            throw new ResourceNotFundException();
        }
        return new DoctorResponseDTO(doctor);

    }

    @Transactional
    public DoctorResponseDTO save(DoctorSaveRequestDTO doctorSaveRequestDto) {
        Doctor doctor = new Doctor(doctorSaveRequestDto);
        doctorRepository.save(doctor);
        return new DoctorResponseDTO(doctor);
    }

    @Transactional
    public DoctorResponseDTO update(Long id, DoctorUpdateRequestDTO doctorUpdateRequestDTO) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow();
        doctor.updateData(doctorUpdateRequestDTO);
        doctorRepository.save(doctor);
        return new DoctorResponseDTO(doctor);
    }

    @Transactional
    public void changeStatus(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow();
        doctor.setStatus(false);
        doctorRepository.save(doctor);
    }


}
