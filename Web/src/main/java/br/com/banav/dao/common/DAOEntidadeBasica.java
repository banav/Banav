package br.com.banav.dao.common;

import br.com.banav.exception.AcessoDBError;
import br.com.banav.model.EntidadeBasica;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.Query;
import javax.persistence.TemporalType;
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

        Query q = getEm().createQuery("select eb from " + className + " eb where eb.dataMovimentacao > :time");
        q.setParameter("time", time, TemporalType.TIMESTAMP);

        return q.getResultList();
    }

    public List<T> listarCortesiaPorDataMovimentoSemPassagem(Date time, Class<T> clazz){
        String className = clazz.getCanonicalName();

        Query q = getEm().createQuery("select eb from Cortesia eb where eb.dataMovimentacao > :time and eb.passagem is null");
        q.setParameter("time", time, TemporalType.TIMESTAMP);

        return q.getResultList();
    }
}
