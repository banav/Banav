package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.model.Viagem;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by GilsonRocha on 29/01/14.
 */
public class ViagemDAO extends DAO<Viagem> {

    public List<Viagem> listar() {
        Query query = getEM().createQuery("select v from Viagem as v order by v.horaSaida asc");
        return query.getResultList();
    }

    public List<Viagem> listarPorDestino(Long destino) {
        Query query = getEM().createQuery("select v from Viagem v where v.destino.id = :destino order by v.horaSaida asc");
        query.setParameter("destino", destino);

        return query.getResultList();
    }
}
