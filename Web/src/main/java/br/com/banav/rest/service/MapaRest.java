package br.com.banav.rest.service;

import br.com.banav.model.MapaArrecadacao;
import br.com.banav.model.MapaViagem;
import br.com.banav.rest.RespostaDTO;
import br.com.banav.rest.dto.MapaArrecadacaoDTO;
import br.com.banav.rest.dto.MapaViagemDTO;
import br.com.banav.service.MapaArrecadacaoSrv;
import br.com.banav.service.UsuarioSrv;
import br.com.banav.service.ViagemSrv;
import br.com.banav.service.ViagemValorClasseSrv;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavocosta on 12/09/15.
 */
@Path("/mapa")
public class MapaRest {

    @Inject
    private MapaArrecadacaoSrv arrecadacaoSrv;

    @Inject
    private UsuarioSrv usuarioSrv;

    @Inject
    private ViagemSrv viagemSrv;

    @Inject
    private ViagemValorClasseSrv viagemValorClasseSrv;

    @Path("/salvar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_XML)
    public RespostaDTO salvarMapaArrecadacao(MapaArrecadacaoDTO arrecadacao){

        try{
            arrecadacaoSrv.salvar(transformDTO(arrecadacao));

            RespostaDTO respostaDTO = new RespostaDTO();
            respostaDTO.setMensagem("Mapa arrecadacao inserido com sucesso!!!");
            respostaDTO.setSucesso(true);
            return respostaDTO;
        }catch (Exception e){
            RespostaDTO respostaDTO = new RespostaDTO();
            respostaDTO.setMensagem(e.getMessage());
            respostaDTO.setSucesso(false);
            return respostaDTO;
        }

    }


    private MapaArrecadacao transformDTO(MapaArrecadacaoDTO dto){
        MapaArrecadacao mapaArrecadacao = new MapaArrecadacao();
        mapaArrecadacao.setValor(dto.getValor());
        mapaArrecadacao.setViagem(viagemSrv.getUm(dto.getViagemId()));
        mapaArrecadacao.setUsuario(usuarioSrv.getUsuarioByLogin(dto.getUsuarioLogin()));

        List<MapaViagem> mapaViagens = new ArrayList<MapaViagem>();

        for(MapaViagemDTO viagemDTO : dto.getMapaViagens()){
            mapaViagens.add(transformMapaViagensDTO(viagemDTO));
        }

        mapaArrecadacao.setMapaViagem(mapaViagens);


        return mapaArrecadacao;
    }

    private MapaViagem transformMapaViagensDTO(MapaViagemDTO mapaViagemDTO){
        MapaViagem mapaViagem = new MapaViagem();
        mapaViagem.setValor(mapaViagemDTO.getValor());
        mapaViagem.setNumeracaoCupom(mapaViagemDTO.getNumeracaoCupom());
        mapaViagem.setQuantidade(mapaViagemDTO.getQuantidade());
        mapaViagem.setViagemValorClasse(viagemValorClasseSrv.getUm(mapaViagemDTO.getViagemValorClasseID()));

        return mapaViagem;
    }

}
