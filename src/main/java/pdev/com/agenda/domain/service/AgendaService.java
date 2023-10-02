package pdev.com.agenda.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pdev.com.agenda.domain.entity.Agenda;
import pdev.com.agenda.domain.entity.Paciente;
import pdev.com.agenda.domain.repository.AgendaRepository;
import pdev.com.agenda.exception.BusinessException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AgendaService {
    @Autowired
    private final AgendaRepository agendaRepository;

    @Autowired
    private final PacienteService pacienteService;

    public List<Agenda> buscarTodos(){
        return agendaRepository.findAll();
    }

    public Optional<Agenda> buscarPorId(Long id){
        return agendaRepository.findById(id);
    }

    public Agenda salvar(Agenda agenda){
        Optional<Paciente> optPaciente = pacienteService.buscarPorId(agenda.getPaciente().getId());

        if(optPaciente.isEmpty()){
            throw new BusinessException("Paciente não encontrado");
        }

        Optional<Agenda> optHorario = agendaRepository.findByHorario(agenda.getHorario());

        if (optHorario.isPresent()){
            throw new BusinessException("Horario indisponivel");
        }

        agenda.setPaciente(optPaciente.get());
        agenda.setDataCriacao(LocalDateTime.now());


        return agendaRepository.save(agenda);
    }
}
