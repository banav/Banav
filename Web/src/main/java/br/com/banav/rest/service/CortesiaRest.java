package br.com.banav.rest.service;

import br.com.banav.model.Classe;
import br.com.banav.model.Cortesia;
import br.com.banav.rest.dto.ClasseDTO;
import br.com.banav.rest.dto.CortesiaDTO;
import br.com.banav.service.ClasseSrv;
import br.com.banav.service.CortesiaSrv;

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
@Path("/cortesia")
public class CortesiaRest {

    @Inject
    private CortesiaSrv cortesiaSrv;

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_XML)
    public List<CortesiaDTO> listar(){
        List<Cortesia> objs = cortesiaSrv.listar();
        return montaDTOs(objs);
    }

    @GET
    @Path("/list/{time}")
    @Produces(MediaType.APPLICATION_XML)
    public List<CortesiaDTO> recuperarLista(@PathParam("time") Long time){
        List<Cortesia> objs = cortesiaSrv.listar(time);

        return montaDTOs(objs);
    }

    private List<CortesiaDTO> montaDTOs(List<Cortesia> objs){

        List<CortesiaDTO> dtos = new ArrayList<CortesiaDTO>();

        if(objs == null)
            return dtos;

        for(Cortesia n : objs){
            CortesiaDTO dto = new CortesiaDTO();
            dto.setAtivo(n.isAtivo());
            dto.setDataMovimentacao(n.getDataMovimentacao());
            dto.setId(n.getId());
            dto.setNome(n.getNome());
            dto.setAutorizante(n.getAutorizante());
            dto.setCpf(n.getCpf());
            dto.setDataCriacao(n.getDataCriacao());
            dto.setViagem(n.getViagem().getId());
            dto.setUsuario(n.getUsuario().getId());
            dto.setSolicitante(n.getSolicitante());
            dto.setRg(n.getRg());
            dto.setPassagemCodigoBarras(n.getPassagem() == null ? null : n.getPassagem().getCodigoBarras());

            dtos.add(dto);
        }

        return dtos;
    }

}
