package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.model.Classe;
import javax.persistence.Query;

import java.util.List;

/**
 * Created by gustavocosta on 01/02/14.
 */
public class ClasseDAO extends DAO<Classe> {

    public List<Classe> listar(){
        Query query = getEm().createQuery("select c from Classe c order by c.nome");
        return query.getResultList();
    }
}
