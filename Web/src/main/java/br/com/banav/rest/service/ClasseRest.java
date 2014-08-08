package br.com.banav.rest.service;

import br.com.banav.model.Classe;
import br.com.banav.model.Navio;
import br.com.banav.rest.dto.ClasseDTO;
import br.com.banav.rest.dto.NavioDTO;
import br.com.banav.service.ClasseSrv;
import br.com.banav.service.NavioSrv;

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
@Path("/classe")
public class ClasseRest {

    @Inject
    private ClasseSrv classeSrv;

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_XML)
    public List<ClasseDTO> listar(){
        List<Classe> objs = classeSrv.listar();
        return montaDTOs(objs);
    }

    @GET
    @Path("/list/{time}")
    @Produces(MediaType.APPLICATION_XML)
    public List<ClasseDTO> recuperarLista(@PathParam("time") Long time){
        List<Classe> objs = classeSrv.listar(time);

        return montaDTOs(objs);
    }

    private List<ClasseDTO> montaDTOs(List<Classe> objs){

        List<ClasseDTO> dtos = new ArrayList<ClasseDTO>();

        if(objs == null)
            return dtos;

        for(Classe n : objs){
            ClasseDTO dto = new ClasseDTO();
            dto.setAtivo(n.isAtivo());
            dto.setDataMovimentacao(n.getDataMovimentacao());
            dto.setId(n.getClasseID());
            dto.setNome(n.getNome());

            dtos.add(dto);
        }

        return dtos;
    }

}
