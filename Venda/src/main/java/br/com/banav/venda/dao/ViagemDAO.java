package br.com.banav.venda.dao;

import br.com.banav.venda.dao.common.DAO;
import br.com.banav.venda.model.Viagem;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by GilsonRocha on 29/01/14.
 */
public class ViagemDAO extends DAO<Viagem> {

    public List<Viagem> listar() {
        Query query = getEm().createQuery("select v from Viagem as v order by v.horaSaida desc");
        return query.getResultList();
    }

    public List<Viagem> listarViagensAtivas(){
        Query query = getEm().createQuery("select v from Viagem as v where v.ativo = true order by v.horaSaida desc");
        return query.getResultList();
    }
}
