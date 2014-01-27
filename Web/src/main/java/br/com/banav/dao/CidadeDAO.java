package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.model.Cidade;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by GilsonRocha on 27/01/14.
 */
public class CidadeDAO extends DAO<Cidade> {

    public List<Cidade> listar() {
        Query query = getEm().createQuery("select c from Cidade as c order by c.nome");
        return query.getResultList();
    }
}
