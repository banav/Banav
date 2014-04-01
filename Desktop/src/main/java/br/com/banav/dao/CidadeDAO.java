package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.model.Cidade;
import br.com.banav.model.Estado;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by GilsonRocha on 27/01/14.
 */
public class CidadeDAO extends DAO<Cidade> {

    public List<Cidade> listar() {
        Query query = getEM().createQuery("select c from Cidade as c order by c.nome");
        return query.getResultList();
    }

    public List<Estado> listarEstado(){
        Query query = getEM().createQuery("select e from Estado e order by e.nome");
        return query.getResultList();
    }
}
