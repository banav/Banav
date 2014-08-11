package br.com.banav.rest.service;

import br.com.banav.model.Classe;
import br.com.banav.model.Porto;
import br.com.banav.rest.dto.ClasseDTO;
import br.com.banav.rest.dto.PortoDTO;
import br.com.banav.service.ClasseSrv;
import br.com.banav.service.PortoSrv;

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
@Path("/porto")
public class PortoRest {

    @Inject
    private PortoSrv srv;

    @GET
    @Path("/list/{time}")
    @Produces(MediaType.APPLICATION_XML)
    public List<PortoDTO> recuperarLista(@PathParam("time") Long time){
        List<Porto> objs = srv.listar(time);

        return montaDTOs(objs);
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_XML)
    public List<PortoDTO> listar(){
        List<Porto> objs = srv.listar();

        return montaDTOs(objs);
    }

    private List<PortoDTO> montaDTOs(List<Porto> objs){
        List<PortoDTO> dtos = new ArrayList<PortoDTO>();

        if(objs == null)
            return dtos;

        for(Porto n : objs){
            PortoDTO dto = new PortoDTO();
            dto.setAtivo(n.isAtivo());
            dto.setDataMovimentacao(n.getDataMovimentacao());
            dto.setId(n.getId());
            dto.setNome(n.getNome());

            dtos.add(dto);
        }

        return dtos;
    }
}
