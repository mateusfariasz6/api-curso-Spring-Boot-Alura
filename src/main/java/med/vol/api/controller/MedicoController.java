package med.vol.api.controller;

import jakarta.validation.Valid;
import med.vol.api.controller.dto.medico.MedicoCadastroDto;
import med.vol.api.controller.dto.medico.MedicoListagemDto;
import med.vol.api.controller.dto.medico.MedicoUpdateDto;
import med.vol.api.models.Medico;
import med.vol.api.repository.MedicoRepository;
import med.vol.api.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private MedicoService medicoService;
    @PostMapping()
    @Transactional
    public void cadastrarMedico(@RequestBody @Valid MedicoCadastroDto medicoCadastroDto){
        medicoRepository.save(new Medico(medicoCadastroDto));
    }

    @GetMapping
    public Page<MedicoListagemDto> findAll(@PageableDefault(size = 10, sort = {"nome"}, direction = Sort.Direction.ASC) Pageable pageable){
         return medicoRepository.findAll(pageable).map(MedicoListagemDto::new);
    }

    @PutMapping
    public void atualizarMedico(@RequestBody @Valid MedicoUpdateDto medicoUpdateDto){
        medicoService.atualizarDados(medicoUpdateDto);
    }

}
