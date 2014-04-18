package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.model.TipoGratuidade;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by GilsonRocha on 27/01/14.
 */
public class TipoGratuidadeDAO extends DAO<TipoGratuidade> {

    public List<TipoGratuidade> listar() {
        Query query = getEm().createQuery("select t from TipoGratuidade t order by t.nome");
        return query.getResultList();
    }
}
