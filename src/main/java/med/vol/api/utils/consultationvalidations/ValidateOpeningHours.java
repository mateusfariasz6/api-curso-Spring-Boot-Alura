package med.vol.api.utils.consultationvalidations;

import med.vol.api.controller.dto.consultation.ConsultationSaveRequestDTO;
import med.vol.api.exceptions.BadRequestException;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class ValidateOpeningHours implements ConsultationValidations {
    @Override
    public void validate(ConsultationSaveRequestDTO consultationSaveRequestDTO) {
        var hour = consultationSaveRequestDTO.date().getHour();
        boolean isSunday = consultationSaveRequestDTO.date().getDayOfWeek().equals(DayOfWeek.SUNDAY);
        if (isSunday || hour < 7 || hour > 18){
            throw new BadRequestException("Outside clinic opening hours!");
        }

    }
}
