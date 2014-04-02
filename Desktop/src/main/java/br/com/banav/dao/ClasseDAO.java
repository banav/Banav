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
        Query query = getEM().createQuery("select c from Classe c order by c.nome");
        return query.getResultList();
    }

    public Classe getClasseEconomica() {
        Query query = getEM().createQuery("select c from Classe c where c.id = 2");
        List<Classe> resultList = query.getResultList();

        if(resultList == null || resultList.isEmpty()) {
            throw new RuntimeException("É necessário configurar a classe Econômica");
        }

        return resultList.get(0);
    }
}
