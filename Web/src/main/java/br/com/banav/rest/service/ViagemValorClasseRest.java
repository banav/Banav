package br.com.banav.rest.service;

import br.com.banav.model.Viagem;
import br.com.banav.model.ViagemValorClasse;
import br.com.banav.rest.dto.ViagemDTO;
import br.com.banav.rest.dto.ViagemValorClasseDTO;
import br.com.banav.service.ViagemSrv;
import br.com.banav.service.ViagemValorClasseSrv;

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
@Path("/viagemvalorclasse")
public class ViagemValorClasseRest {

    @Inject
    private ViagemValorClasseSrv srv;

    @GET
    @Path("/list/{time}")
    @Produces(MediaType.APPLICATION_XML)
    public List<ViagemValorClasseDTO> recuperarLista(@PathParam("time") Long time){
        List<ViagemValorClasse> objs = srv.listar(time);

        return montaDTOs(objs);
    }


    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_XML)
    public List<ViagemValorClasseDTO> listar(){
        List<ViagemValorClasse> objs = srv.listar();
        return montaDTOs(objs);
    }

    private List<ViagemValorClasseDTO> montaDTOs (List<ViagemValorClasse> objs){
        List<ViagemValorClasseDTO> dtos = new ArrayList<ViagemValorClasseDTO>();

        if(objs == null)
            return dtos;

        for(ViagemValorClasse n : objs){
            ViagemValorClasseDTO dto = new ViagemValorClasseDTO();



            dto.setAtivo(n.isAtivo());
            dto.setDataMovimentacao(n.getDataMovimentacao());
            dto.setId(n.getId());

            dto.setNavioID(n.getNavioClasse().getNavio().getNavioID());

            dto.setAceitaGratuidade(n.getAceitaGratuidade());
            dto.setClasseID(n.getNavioClasse().getClasse().getClasseID());
            dto.setValor(n.getValor());
            dto.setValorMeia(n.getValorMeia());
            dto.setViagemID(n.getViagem().getId());

            dtos.add(dto);
        }

        return dtos;
    }
}
