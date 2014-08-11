package br.com.banav.rest.service;

import br.com.banav.model.Navio;
import br.com.banav.rest.dto.NavioDTO;
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
@Path("/navio")
public class NavioRest {

    @Inject
    private NavioSrv navioSrv;

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_XML)
    public List<NavioDTO> listar(){
        List<Navio> navios = navioSrv.listar();


        List<NavioDTO> dtos = new ArrayList<NavioDTO>();

        for(Navio n : navios){
            dtos.add(transformaNavioDTO(n));
        }
        return dtos;
    }

    @GET
    @Path("/list/{time}")
    @Produces(MediaType.APPLICATION_XML)
    public List<NavioDTO> recuperarListaNavio(@PathParam("time") Long time){
        List<Navio> navios = navioSrv.listar(time);

        List<NavioDTO> dtos = new ArrayList<NavioDTO>();

        for(Navio n : navios){

            dtos.add(transformaNavioDTO(n));
        }

        return dtos;
    }

    private NavioDTO transformaNavioDTO(Navio n){
        NavioDTO dto = new NavioDTO();
        dto.setAtivo(n.isAtivo());
        dto.setDataMovimentacao(n.getDataMovimentacao());
        dto.setId(n.getNavioID());
        dto.setNome(n.getNome());

        return dto;
    }
}
