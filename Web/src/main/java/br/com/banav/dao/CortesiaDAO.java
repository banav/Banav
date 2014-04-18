package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.model.Cortesia;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by GilsonRocha on 27/01/14.
 */
public class CortesiaDAO extends DAO<Cortesia> {

    public List<Cortesia> listar() {
        Query query = getEm().createQuery("select c from Cortesia as c order by c.dataCriacao desc");
        return query.getResultList();
    }
}
