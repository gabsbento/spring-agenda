package pdev.com.agenda.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Paciente", description = "Rotas para o Paciente")
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

    @Operation(
            summary = "Lista todos os Pacientes",
            description = "Recupera todos os Pacientes e suas informações",
            tags = { "Paciente", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Paciente.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
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
