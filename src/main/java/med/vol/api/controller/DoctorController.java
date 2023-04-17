package med.vol.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.vol.api.controller.dto.doctor.DoctorResponseDTO;
import med.vol.api.controller.dto.doctor.DoctorSaveRequestDTO;
import med.vol.api.controller.dto.doctor.DoctorUpdateRequestDTO;
import med.vol.api.service.DoctorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "medicos")
@RequiredArgsConstructor
public class DoctorController {


    private final DoctorService doctorService;

    @PostMapping()
    public ResponseEntity<Void> save(@RequestBody @Valid DoctorSaveRequestDTO doctorSaveRequestDto) {
        doctorService.save(doctorSaveRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> findAll(@PageableDefault(size = 10, sort = {"name"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(doctorService.listAll(pageable).getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid DoctorUpdateRequestDTO doctorUpdateRequestDTO) {
        doctorService.update(id, doctorUpdateRequestDTO);
        return ResponseEntity.status(200).build();
    }

    @PatchMapping("delete/{id}")
    public ResponseEntity<Void> changeStatusOfDoctor(@PathVariable Long id) {
        doctorService.changeStatus(id);
        return ResponseEntity.ok().build();
    }


}
