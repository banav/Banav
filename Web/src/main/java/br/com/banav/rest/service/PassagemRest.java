package br.com.banav.rest.service;

import br.com.banav.model.*;
import br.com.banav.service.PassagemSrv;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.io.Serializable;
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
    public void salvarPassagem(@QueryParam("codigoBarras") String codigoBarras,
                               @QueryParam("vvc_id") Long viagemValorClasse,
                               @QueryParam("gratuidade") Boolean gratuidade,
                               @QueryParam("valor") Double valor,
                               @QueryParam("data_emissao") Long dataEmissao){

        Passagem passagem = new Passagem();

        passagem.setCodigoBarras(codigoBarras);
        passagem.setGratuidade(gratuidade);

        ViagemValorClasse _viagemValorClasse = new ViagemValorClasse();
        _viagemValorClasse.setId(viagemValorClasse);
        passagem.setViagemValorClasse(_viagemValorClasse);

        passagem.setValor(valor);

        PassagemHistorico passagemHistorico = new PassagemHistorico();
        passagemHistorico.setData(new Date(dataEmissao));
        passagemHistorico.setPassagemMovimento(PassagemMovimento.MARCADA);
        passagemHistorico.setPassagem(passagem);

        passagem.getHistorico().add(passagemHistorico);

        passagemSrv.salvar(passagem);






    }

    public static void  main (String args[]){
        String codigoBarras = "14080906300101020001";


        //System.out.print(calendar);
    }
}
