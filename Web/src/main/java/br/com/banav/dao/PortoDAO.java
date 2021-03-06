package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.dao.common.DAOEntidadeBasica;
import br.com.banav.model.Navio;
import br.com.banav.model.Porto;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by GilsonRocha on 29/01/14.
 */
public class PortoDAO extends DAOEntidadeBasica<Porto> {

    public List<Porto> listar() {
        Query query = getEm().createQuery("select p from Porto as p order by p.nome");
        return query.getResultList();
    }
}