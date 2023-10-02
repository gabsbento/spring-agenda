package pdev.com.agenda.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pdev.com.agenda.domain.entity.Paciente;
import pdev.com.agenda.domain.repository.PacienteRepository;
import pdev.com.agenda.exception.BusinessException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente salvar(Paciente paciente){
        boolean existeCpf = false;
        Optional<Paciente> optPaciente = pacienteRepository.findByCpf(paciente.getCpf());
        if(optPaciente.isPresent()){
            if(!optPaciente.get().getId().equals(paciente.getId())){
                existeCpf = true;
            }
        }

        if(existeCpf){
            throw new RuntimeException();
        }
        return pacienteRepository.save(paciente);
    }

    public void excluir(Long id){
        pacienteRepository.deleteById(id);
    }

    public Optional<Paciente> buscarPorId(Long id){
        return pacienteRepository.findById(id);
    }

    public List<Paciente> buscarTodos(){
        return pacienteRepository.findAll();
    }

    public Paciente alterar(Long id, Paciente paciente){
        Optional<Paciente> response = this.buscarPorId(id);

        if(response.isEmpty()){
            throw new RuntimeException();
        }

        paciente.setId(id);
        return this.salvar(paciente);
    }

}
