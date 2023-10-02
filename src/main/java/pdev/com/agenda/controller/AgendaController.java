package pdev.com.agenda.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdev.com.agenda.domain.entity.Agenda;
import pdev.com.agenda.domain.service.AgendaService;
import pdev.com.agenda.mapper.AgendaMapper;
import pdev.com.agenda.request.AgendaRequest;
import pdev.com.agenda.response.AgendaResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agenda")
@RequiredArgsConstructor
public class AgendaController {

    private final AgendaService agendaService;
    private final AgendaMapper agendaMapper;

    @GetMapping
    public ResponseEntity<List<AgendaResponse>> buscarTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(agendaMapper.toAgendaResponseList(agendaService.buscarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaResponse> buscarPorId(@PathVariable Long id){
        Optional<Agenda> response = agendaService.buscarPorId(id);
        if(response.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(agendaMapper.toAgendaResponse(response.get()));
    }

    @PostMapping
    public ResponseEntity<AgendaResponse> criar(@Valid @RequestBody AgendaRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(agendaMapper.toAgendaResponse(agendaService.salvar(agendaMapper.toAgenda(request))));
    }
}
