package br.com.banav.rest.service;

import br.com.banav.model.NavioClasse;
import br.com.banav.model.Porto;
import br.com.banav.rest.dto.NavioClasseDTO;
import br.com.banav.rest.dto.PortoDTO;
import br.com.banav.service.NavioClasseSrv;
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
@Path("/navioclasse")
public class NavioClasseRest {

    @Inject
    private NavioClasseSrv srv;


    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_XML)
    public List<NavioClasseDTO> listar(){
        List<NavioClasse> objs = srv.listar();
        return montaDTOs(objs);
    }

    @GET
    @Path("/list/{time}")
    @Produces(MediaType.APPLICATION_XML)
    public List<NavioClasseDTO> recuperarLista(@PathParam("time") Long time){
        List<NavioClasse> objs = srv.listar(time);

        return montaDTOs(objs);
    }

    private List<NavioClasseDTO> montaDTOs(List<NavioClasse> objs){
        List<NavioClasseDTO> dtos = new ArrayList<NavioClasseDTO>();

        if(objs == null)
            return dtos;

        for(NavioClasse n : objs){

            NavioClasseDTO dto = new NavioClasseDTO();

            dto.setQuantidade(n.getQuantidade());
            dto.setDataMovimentacao(n.getDataMovimentacao());
            dto.setAtivo(n.isAtivo());
            dto.setClasseID(n.getClasse().getClasseID());
            dto.setNavioID(n.getNavio().getNavioID());


            dtos.add(dto);
        }

        return dtos;
    }


}
