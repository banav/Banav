package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.dao.common.DAOLocalEntidadeBasica;
import br.com.banav.model.EntidadeBasica;
import br.com.banav.model.Navio;

import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by GilsonRocha on 29/01/14.
 */
public class NavioDAO extends DAOLocalEntidadeBasica<Navio> {

    public List<Navio> listar() {
        Query query = getEM().createQuery("select n from Navio as n order by n.nome");
        return query.getResultList();
    }

    @Override
    public void sincronizar(Navio entidadeBasica) {

        String exists = "select count(1) from offline.navio where id = :id";

        BigInteger count = (BigInteger) getEM().createNativeQuery(exists)
                .setParameter("id", entidadeBasica.getNavioID())
                .getSingleResult();

        String queryStr = "";

        if(count.intValue() == 0){
            queryStr = "INSERT INTO offline.navio(" +
                    " id, nome, datamovimentacao, ativo) " +
                    " VALUES (:id, :nome, :data, :ativo)";
        }
        else{
            queryStr = "UPDATE offline.navio" +
                    " SET  nome= :nome, datamovimentacao= :data, ativo= :ativo" +
                    " WHERE id= :id";
        }

        boolean isAlive = getEM().getTransaction().isActive();
        if(!isAlive) {
            getEM().getTransaction().begin();
        }

        Query q = getEM().createNativeQuery(queryStr);
        q.setParameter("id", entidadeBasica.getNavioID());
        q.setParameter("nome", entidadeBasica.getNome());
        q.setParameter("data", entidadeBasica.getDataMovimentacao());
        q.setParameter("ativo", entidadeBasica.isAtivo());

        try{
            q.executeUpdate();
            if(!isAlive) {
                getEM().getTransaction().commit();
            }
        }catch (Exception ex) {
            if(!isAlive) {
                getEM().getTransaction().rollback();
            }
        }

    }
}