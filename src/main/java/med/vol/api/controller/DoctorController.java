package med.vol.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.vol.api.controller.dto.doctor.DoctorResponseDTO;
import med.vol.api.controller.dto.doctor.DoctorSaveRequestDTO;
import med.vol.api.controller.dto.doctor.DoctorUpdateRequestDTO;
import med.vol.api.service.DoctorService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "medico")
@RequiredArgsConstructor
public class DoctorController {


    private final DoctorService doctorService;

    @PostMapping()
    public ResponseEntity<DoctorResponseDTO> save(@RequestBody @Valid DoctorSaveRequestDTO doctorSaveRequestDto, UriComponentsBuilder uriComponentsBuilder) {
        DoctorResponseDTO doctor = doctorService.save(doctorSaveRequestDto);
        URI uri = uriComponentsBuilder.path("medico/{id}").buildAndExpand(doctor.id()).toUri();
        return ResponseEntity.created(uri).body(doctor);
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> findAll(@PageableDefault(size = 10, sort = {"name"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(doctorService.listAllByStatusIsTrue(pageable).getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> update(@PathVariable Long id, @RequestBody DoctorUpdateRequestDTO doctorUpdateRequestDTO) {
        return ResponseEntity.ok(doctorService.update(id, doctorUpdateRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> changeStatusOfDoctor(@PathVariable Long id) {
        doctorService.changeStatus(id);
        return ResponseEntity.noContent().build();
    }


}
