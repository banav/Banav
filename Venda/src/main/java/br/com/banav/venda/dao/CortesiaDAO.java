package br.com.banav.venda.dao;

import br.com.banav.venda.dao.common.DAO;
import br.com.banav.venda.model.Cortesia;

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

    public List<Cortesia> listarSemPassagem() {
        Query query = getEm().createQuery("select c from Cortesia as c where c.passagem is null order by c.dataCriacao desc");
        return query.getResultList();
    }
}
