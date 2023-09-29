package pdev.com.agenda.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pdev.com.agenda.domain.entity.Paciente;
import pdev.com.agenda.request.PacienteRequest;
import pdev.com.agenda.response.PacienteResponse;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PacienteMapper {

    private final ModelMapper mapper;

    public Paciente toPaciente(PacienteRequest request){
        return mapper.map(request, Paciente.class);
    }

    public PacienteResponse toPacienteResponse(Paciente paciente){
        return mapper.map(paciente, PacienteResponse.class);
    }

    public List<PacienteResponse> toPacientesResponseList(List<Paciente> pacientes){
        return pacientes.stream().map(this::toPacienteResponse).collect(Collectors.toList());
    }

}
