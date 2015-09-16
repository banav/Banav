package br.com.banav.dao;

import br.com.banav.dao.common.DAOLocal;
import br.com.banav.model.MapaArrecadacao;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by gustavocosta on 13/09/15.
 */
public class MapaArrecadacaoDAO extends DAOLocal<MapaArrecadacao> {

    public List<MapaArrecadacao> listarNaoEnviadas() {
        Query query = getEM().createQuery("select p from MapaArrecadacao as p join fetch p.mapaViagem where p.enviado = false");
        return query.getResultList();
    }

}
