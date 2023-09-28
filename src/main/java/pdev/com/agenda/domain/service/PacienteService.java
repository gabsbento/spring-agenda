package pdev.com.agenda.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pdev.com.agenda.domain.entity.Paciente;
import pdev.com.agenda.domain.repository.PacienteRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public void salvar(Paciente paciente){
        pacienteRepository.save(paciente);
    }

    public void excluir(Long id){
        pacienteRepository.deleteById(id);
    }

    public Paciente buscarPorId(Long id){
        return pacienteRepository.findById(id).orElse(null);
    }

    public List<Paciente> buscarTodos(){
        return pacienteRepository.findAll();
    }

}
