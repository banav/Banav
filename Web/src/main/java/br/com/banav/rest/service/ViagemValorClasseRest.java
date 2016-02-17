package br.com.banav.rest.service;

import br.com.banav.model.Viagem;
import br.com.banav.model.ViagemValorClasse;
import br.com.banav.rest.dto.ViagemValorClasseDTO;
import br.com.banav.rest.dto.vendas.ResultadoConsultaDTO;
import br.com.banav.service.ViagemValorClasseSrv;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gustavocosta on 06/08/14.
 */
@Path("/viagemvalorclasse")
public class ViagemValorClasseRest {

    @Inject
    private ViagemValorClasseSrv srv;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response query(@QueryParam("origem") Long origem, @QueryParam("destino") Long destino, @QueryParam("dia") Date dia) throws ParseException {
        List<ViagemValorClasse> viagemValorClasseList = srv.listarPor(origem, destino, dia);
        ResultadoConsultaDTO resultadoConsultaDTO = new ResultadoConsultaDTO(viagemValorClasseList);

        return Response.ok(resultadoConsultaDTO).build();
    }

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
