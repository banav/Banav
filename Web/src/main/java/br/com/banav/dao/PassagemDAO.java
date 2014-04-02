package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.model.Passagem;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by GilsonRocha on 29/01/14.
 */
public class PassagemDAO extends DAO<Passagem> {

    public List<Passagem> listar() {
        Query query = getEm().createQuery("select p from Passagem as p order by p.id");
        return query.getResultList();
    }
}