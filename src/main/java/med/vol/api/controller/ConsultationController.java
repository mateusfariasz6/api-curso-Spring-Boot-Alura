package med.vol.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.vol.api.controller.dto.consultation.ConsultationResponseDTO;
import med.vol.api.controller.dto.consultation.ConsultationSaveRequestDTO;
import med.vol.api.service.ConsultationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController()
@RequestMapping("consultation")
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;
    @PostMapping
    public ResponseEntity<ConsultationResponseDTO> toSchedule(@RequestBody @Valid ConsultationSaveRequestDTO consultationSaveRequestDTO, UriComponentsBuilder uriComponentsBuilder){
        ConsultationResponseDTO consultation = consultationService.toSchedule(consultationSaveRequestDTO);
        URI uri = uriComponentsBuilder.path("/consultation").buildAndExpand(consultation.id()).toUri();
        return ResponseEntity.created(uri).body(consultation);

    }
}
