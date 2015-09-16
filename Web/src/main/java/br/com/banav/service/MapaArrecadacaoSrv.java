package br.com.banav.service;

import br.com.banav.dao.CortesiaDAO;
import br.com.banav.dao.MapaArrecadacaoDAO;
import br.com.banav.dao.PassagemDAO;
import br.com.banav.dao.PassagemHistoricoDAO;
import br.com.banav.model.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by GustavoCosta on 12/09/2015.
 */
@Stateless
public class MapaArrecadacaoSrv {

    @Inject private MapaArrecadacaoDAO mapaArrecadacaoDAO;

    public void salvar(MapaArrecadacao arrecadacao) {

        mapaArrecadacaoDAO.salvar(arrecadacao);
    }
}
