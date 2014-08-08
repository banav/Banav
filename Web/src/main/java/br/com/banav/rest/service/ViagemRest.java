package br.com.banav.rest.service;

import br.com.banav.model.Porto;
import br.com.banav.model.Viagem;
import br.com.banav.rest.dto.PortoDTO;
import br.com.banav.rest.dto.ViagemDTO;
import br.com.banav.service.PortoSrv;
import br.com.banav.service.ViagemSrv;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavocosta on 06/08/14.
 */
@Path("/viagem")
public class ViagemRest {

    @Inject
    private ViagemSrv srv;

    @GET
    @Path("/list/{time}")
    @Produces(MediaType.APPLICATION_XML)
    public List<ViagemDTO> recuperarLista(@PathParam("time") Long time){
        List<Viagem> objs = srv.listar(time);

        return montaDTOs(objs);
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_XML)
    public List<ViagemDTO> listar(){
        List<Viagem> objs = srv.listar();
        return montaDTOs(objs);
    }

    private List<ViagemDTO> montaDTOs(List<Viagem> objs){
        List<ViagemDTO> dtos = new ArrayList<ViagemDTO>();

        if(objs == null)
            return dtos;

        for(Viagem n : objs){
            ViagemDTO dto = new ViagemDTO();
            dto.setAtivo(n.isAtivo());
            dto.setDataMovimentacao(n.getDataMovimentacao());
            dto.setId(n.getId());
            dto.setHoraSaida(n.getHoraSaida());
            dto.setHoraChegada(n.getHoraChegada());
            dto.setDestinoID(n.getDestino().getId());
            dto.setOrigemID(n.getOrigem().getId());
            dto.setNavioID(n.getNavio().getNavioID());

            dtos.add(dto);
        }

        return dtos;
    }
}
