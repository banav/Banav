package br.com.banav.dao.common;

import br.com.banav.model.EntidadeBasica;

import javax.persistence.Query;
import java.util.Date;

/**
 * Created by gustavocosta on 06/08/14.
 */
public abstract class DAOLocalEntidadeBasica<T extends EntidadeBasica> extends DAOLocal<T> {

    public Date ultimaAtualizacao(Class<T> clazz){

        Query query = getEM().createQuery("select max(ul.dataMovimentacao) from " +  clazz.getCanonicalName() + " ul");

        try{
            Date data = (Date)query.getSingleResult();
            data = new Date(data.getTime());
            return data;
        }
        catch (Exception e){
            return null;
        }
    }

    public abstract void sincronizar(T entidadeBasica);



}

