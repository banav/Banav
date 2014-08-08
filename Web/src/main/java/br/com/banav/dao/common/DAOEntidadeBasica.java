package br.com.banav.dao.common;

import br.com.banav.exception.AcessoDBError;
import br.com.banav.model.EntidadeBasica;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by gustavocosta on 06/08/14.
 */
public class DAOEntidadeBasica<T extends EntidadeBasica> extends DAO<T> {


    @Override
    public void remover(Serializable id, Class<T> clazz) throws AcessoDBError {
        EntidadeBasica en = getUm(id, clazz);
        en.setAtivo(false);
    }

    public List<T> listarPorDataMovimento(Date time, Class<T> clazz){


        String className = clazz.getCanonicalName();

        Query q = getEm().createQuery("select eb from " + className + " eb where eb.dataMovimentacao >= :time");
        q.setParameter("time", time);

        return q.getResultList();

    }
}
