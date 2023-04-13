package med.vol.api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import med.vol.api.controller.dto.medico.MedicoCadastroDto;
import med.vol.api.controller.dto.medico.MedicoListagemDto;
import med.vol.api.controller.dto.medico.MedicoUpdateDto;
import med.vol.api.models.Medico;
import med.vol.api.repository.MedicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public Page<MedicoListagemDto> listAll(Pageable pageable){
        return medicoRepository.findAll(pageable).map(MedicoListagemDto::new);
    }

    public MedicoListagemDto findById(Long id) {
        Medico medico = medicoRepository.findById(id).orElseThrow();
        return new MedicoListagemDto(medico);

    }
    @Transactional
    public void save(MedicoCadastroDto medicoCadastroDto){
        medicoRepository.save(new Medico(medicoCadastroDto));
    }
    //@Transactional
//    public MedicoListagemDto update(MedicoUpdateDto medicoUpdateDto){
//        Medico medico = medicoRepository.findById(medicoUpdateDto.id()).orElseThrow();
//
//
//    }


}
