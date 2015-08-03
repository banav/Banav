package br.com.banav.venda.dao;

import br.com.banav.venda.dao.common.DAO;
import br.com.banav.venda.model.Navio;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by GilsonRocha on 29/01/14.
 */
public class NavioDAO extends DAO<Navio> {

    public List<Navio> listar() {
        Query query = getEm().createQuery("select n from Navio as n order by n.nome");
        return query.getResultList();
    }


    public Navio getUmComClasse(Serializable id){
        String query = "select n from Navio n join fetch n.classes where n.navioID = :navio";
        Query q = getEm().createQuery(query);

        q.setParameter("navio", id);

        return (Navio) q.getSingleResult();
    }
}