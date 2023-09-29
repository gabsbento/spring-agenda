package pdev.com.agenda.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdev.com.agenda.domain.entity.Paciente;
import pdev.com.agenda.domain.service.PacienteService;
import pdev.com.agenda.mapper.PacienteMapper;
import pdev.com.agenda.request.PacienteRequest;
import pdev.com.agenda.response.PacienteResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("paciente")
public class PacienteController {


    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private PacienteMapper pacienteMapper;

    @PostMapping
    public ResponseEntity<PacienteResponse> criar(@Valid @RequestBody PacienteRequest request){
        Paciente paciente = pacienteMapper.toPaciente(request);
        Paciente pacienteSalvo = pacienteService.salvar(paciente);
        PacienteResponse pacienteResponse = pacienteMapper.toPacienteResponse(pacienteSalvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteResponse);
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponse>> listar(){
        List<Paciente> pacientes = pacienteService.buscarTodos();
        List<PacienteResponse> response = pacienteMapper.toPacientesResponseList(pacientes);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> buscarPorId(@PathVariable Long id){
        Optional<Paciente> response = pacienteService.buscarPorId(id);
        if(response.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(pacienteMapper.toPacienteResponse(response.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> atualizar(@PathVariable Long id, @Valid @RequestBody PacienteRequest request){
        Paciente paciente = pacienteMapper.toPaciente(request);

        Paciente updatePaciente = pacienteService.alterar(id, paciente);
        return ResponseEntity.status(HttpStatus.OK).body(pacienteMapper.toPacienteResponse(updatePaciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id){
        pacienteService.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
