package med.vol.api.controller;

import med.vol.api.controller.dto.medico.MedicoCadastroDto;
import med.vol.api.models.Medico;
import med.vol.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;
    @PostMapping()
    public void cadastrarMedico(@RequestBody MedicoCadastroDto medicoCadastroDto){
        medicoRepository.save(new Medico(medicoCadastroDto));
    }

}
