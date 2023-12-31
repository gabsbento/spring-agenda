package pdev.com.agenda.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pdev.com.agenda.domain.entity.Agenda;
import pdev.com.agenda.request.AgendaRequest;
import pdev.com.agenda.response.AgendaResponse;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AgendaMapper {

    private final ModelMapper mapper;

    public Agenda toAgenda(AgendaRequest request) {
        return mapper.map(request, Agenda.class);
    }

    public AgendaResponse toAgendaResponse(Agenda agenda) {
        return mapper.map(agenda, AgendaResponse.class);
    }

    public List<AgendaResponse> toAgendaResponseList(List<Agenda> agendas){
        return agendas.stream().map(this::toAgendaResponse).collect(Collectors.toList());
    }
}
