package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.model.Navio;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by GilsonRocha on 29/01/14.
 */
public class NavioDAO extends DAO<Navio> {

    public List<Navio> listar() {
        Query query = getEM().createQuery("select n from Navio as n order by n.nome");
        return query.getResultList();
    }
}