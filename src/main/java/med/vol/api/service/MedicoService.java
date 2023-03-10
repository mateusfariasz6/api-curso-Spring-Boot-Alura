package med.vol.api.service;

import med.vol.api.controller.dto.medico.MedicoUpdateDto;
import med.vol.api.models.Medico;
import med.vol.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Service
public class MedicoService {
    @Autowired
    MedicoRepository medicoRepository;
    public Medico atualizarDados(MedicoUpdateDto medicoUpdateDto){
        Optional<Medico> medico = medicoRepository.findById(medicoUpdateDto.id());
        if (!medico.isPresent()){
            return null;
        }else {
            medico.get().setNome(medicoUpdateDto.nome());
            medico.get().setEndereco(medicoUpdateDto.);
        }
    }
}
