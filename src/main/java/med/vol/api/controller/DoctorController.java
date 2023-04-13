package med.vol.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.vol.api.controller.dto.medico.MedicoCadastroDto;
import med.vol.api.controller.dto.medico.MedicoListagemDto;
import med.vol.api.controller.dto.medico.MedicoUpdateDto;
import med.vol.api.repository.MedicoRepository;
import med.vol.api.service.MedicoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "medicos")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoRepository medicoRepository;

    private final MedicoService medicoService;
    @PostMapping()
    public void cadastrarMedico(@RequestBody @Valid MedicoCadastroDto medicoCadastroDto){
        medicoService.save(medicoCadastroDto);
    }

    @GetMapping
    public Page<MedicoListagemDto> findAll(@PageableDefault(size = 10, sort = {"nome"}, direction = Sort.Direction.ASC) Pageable pageable){
         return medicoService.listAll(pageable);
    }
    @GetMapping("/{id}")
    public MedicoListagemDto findById(@PathVariable Long id){
        return medicoService.findById(id);
    }
//    @PutMapping
//    public void atualizarMedico(@RequestBody @Valid MedicoUpdateDto medicoUpdateDto){
//        medicoService.update(medicoUpdateDto);
//    }

}
