package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.dao.common.DAOEntidadeBasica;
import br.com.banav.model.NavioClasse;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by gustavocosta on 07/02/14.
 */
public class NavioClasseDAO extends DAOEntidadeBasica<NavioClasse> {

    public List<NavioClasse> listarPor(Long navioId) {
        Query query = getEm().createQuery("select nc from NavioClasse as nc where nc.navio.id = :id");
        query.setParameter("id", navioId);

        return query.getResultList();
    }

    public List<NavioClasse> listar(){
        Query query = getEm().createQuery("select nc from NavioClasse  nc");
        return query.getResultList();
    }
}
