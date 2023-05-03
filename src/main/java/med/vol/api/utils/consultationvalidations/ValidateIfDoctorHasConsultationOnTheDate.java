package med.vol.api.utils.consultationvalidations;

import lombok.RequiredArgsConstructor;
import med.vol.api.controller.dto.consultation.ConsultationSaveRequestDTO;
import med.vol.api.repository.ConsultationRepository;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class ValidateIfDoctorHasConsultationOnTheDate implements ConsultationValidations{
    private final ConsultationRepository consultationRepository;
    @Override
    public void validate(ConsultationSaveRequestDTO consultationSaveRequestDTO) {
        if (consultationSaveRequestDTO.doctorId() == null){
            return;
        }
        Long doctorId = consultationSaveRequestDTO.doctorId();
        LocalDateTime date = consultationSaveRequestDTO.date();
        boolean existConsultation = consultationRepository.existsByDoctorIdAndDate(doctorId, date);
    }
}
