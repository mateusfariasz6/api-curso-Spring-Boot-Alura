package med.vol.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.vol.api.controller.dto.consultation.ConsultationResponseDTO;
import med.vol.api.controller.dto.consultation.ConsultationSaveRequestDTO;
import med.vol.api.service.ConsultationService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;

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
    @GetMapping
    public ResponseEntity<List<ConsultationResponseDTO>> listAll(@PageableDefault(size = 10, sort = {"date"},direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(consultationService.listAll(pageable));
    }
    @GetMapping(value = "/doctor/{id}")
    public ResponseEntity<List<ConsultationResponseDTO>> listAllByDoctorId(@PathVariable Long id, @PageableDefault(size = 10, sort = {"date"},direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(consultationService.listAllByDoctorId(id, pageable));
    }

    @GetMapping(value = "/patient/{id}")
    public ResponseEntity<List<ConsultationResponseDTO>> listAllByPatientId(@PathVariable Long id, @PageableDefault(size = 10, sort = {"date"},direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(consultationService.listAllByPatientId(id, pageable));

    }
}
