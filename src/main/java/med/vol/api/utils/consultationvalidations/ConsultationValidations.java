package med.vol.api.utils.consultationvalidations;

import med.vol.api.controller.dto.consultation.ConsultationSaveRequestDTO;

public interface ConsultationValidations {
    void validate(ConsultationSaveRequestDTO consultationSaveRequestDTO);
}
