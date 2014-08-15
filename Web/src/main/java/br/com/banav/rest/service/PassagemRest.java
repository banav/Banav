package br.com.banav.rest.service;

import br.com.banav.model.*;
import br.com.banav.rest.RespostaDTO;
import br.com.banav.service.PassagemSrv;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by gustavocosta on 09/08/14.
 */
@Path("/passagem")
public class PassagemRest  implements Serializable {


    @Inject
    private PassagemSrv passagemSrv;

    @GET
    @Path("/salvar")
    @Produces(MediaType.APPLICATION_XML)
    public RespostaDTO salvarPassagem(@QueryParam("codigoBarras") String codigoBarras,
                               @QueryParam("vvc_id") Long viagemValorClasse,
                               @QueryParam("gratuidade") Boolean gratuidade,
                               @QueryParam("valor") Double valor,
                               @QueryParam("data_emissao") Long dataEmissao){

        Passagem passagem = new Passagem();

        passagem.setHistorico(new ArrayList<PassagemHistorico>());
        passagem.setCodigoBarras(codigoBarras);
        passagem.setGratuidade(gratuidade);
        passagem.setCheckin(false);

        ViagemValorClasse _viagemValorClasse = new ViagemValorClasse();
        _viagemValorClasse.setId(viagemValorClasse);
        passagem.setViagemValorClasse(_viagemValorClasse);

        passagem.setValor(valor);

        PassagemHistorico passagemHistorico = new PassagemHistorico();
        passagemHistorico.setData(new Date(dataEmissao));
        passagemHistorico.setPassagemMovimento(PassagemMovimento.MARCADA);
        passagemHistorico.setPassagem(passagem);

        passagem.getHistorico().add(passagemHistorico);


        RespostaDTO respostaDTO = new RespostaDTO();
        try{
            passagemSrv.salvar(passagem);
            respostaDTO.setMensagem("Passagem" + codigoBarras+  "salva com sucesso");
            respostaDTO.setSucesso(true);
        }
        catch (Exception e){
            respostaDTO.setMensagem("Passagem " + codigoBarras + " nao salvou. "  + e.getMessage());
            respostaDTO.setSucesso(false);
        }

        return respostaDTO;
    }


    @GET
    @Path("/cancelar")
    @Produces(MediaType.APPLICATION_XML)
    public RespostaDTO cancelarPassagem(@QueryParam("codigoBarras") String codigoBarras,
                                        @QueryParam("data_cancelamento") Long dataCancelamento){

        RespostaDTO respostaDTO = new RespostaDTO();

        try{
            passagemSrv.cancelarPassagem(codigoBarras, new Date(dataCancelamento));
            respostaDTO.setMensagem("Passagem " + codigoBarras +" cancelada com sucesso!");
            respostaDTO.setSucesso(true);
        }
        catch (Exception e){
            respostaDTO.setMensagem("Passagem " + codigoBarras +" nao foi cancelada! " + e.getMessage());
            respostaDTO.setSucesso(true);
        }
        return respostaDTO;
    }

}
