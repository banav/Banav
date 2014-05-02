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
        Query query = getEM().createQuery("select c from Cortesia as c where c.passagem is null order by c.nome");
        return query.getResultList();
    }
}
