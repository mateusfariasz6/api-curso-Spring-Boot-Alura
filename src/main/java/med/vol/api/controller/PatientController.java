package med.vol.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.vol.api.controller.dto.patient.PatientResponseDTO;
import med.vol.api.controller.dto.patient.PatientSaveRequestDTO;
import med.vol.api.controller.dto.patient.PatientUpdateRequestDTO;
import med.vol.api.service.PatientService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> listAll(@PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(patientService.listAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PatientResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(patientService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<Void> save(@RequestBody @Valid PatientSaveRequestDTO patientSaveRequestDTO){
        patientService.save(patientSaveRequestDTO);
        URI uri = URI.create("/patient");
        return ResponseEntity.created(uri).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> update(@PathVariable Long id, @RequestBody @Valid PatientUpdateRequestDTO patientUpdateRequestDTO){
        return ResponseEntity.ok(patientService.update(id, patientUpdateRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
