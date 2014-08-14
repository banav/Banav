package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.dao.common.DAOLocalEntidadeBasica;
import br.com.banav.model.Porto;

import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by GilsonRocha on 29/01/14.
 */
public class PortoDAO extends DAOLocalEntidadeBasica<Porto> {

    public List<Porto> listar() {
        Query query = getEM().createQuery("select p from Porto as p order by p.nome");
        return query.getResultList();
    }

    public List<Porto> listarDestinosAgendados() {
        Query query = getEM().createQuery("select p from Porto p where p.id  in (select v.destino.id from Viagem v)");
        return query.getResultList();
    }

    @Override
    public void sincronizar(Porto entidadeBasica) {
        String exists = "select count(1) from offline.porto where id = :id";

        BigInteger count = (BigInteger) getEM().createNativeQuery(exists)
                .setParameter("id", entidadeBasica.getId())
                .getSingleResult();

        String queryStr = "";

        if(count.intValue() == 0){
            queryStr = "INSERT INTO offline.porto(" +
                    " id, nome, datamovimentacao, ativo) " +
                    " VALUES (:id, :nome, :data, :ativo)";
        }
        else{
            queryStr = "UPDATE offline.porto" +
                    " SET  nome= :nome, datamovimentacao= :data, ativo= :ativo" +
                    " WHERE id= :id";
        }

        boolean isAlive = getEM().getTransaction().isActive();
        if(!isAlive) {
            getEM().getTransaction().begin();
        }

        Query q = getEM().createNativeQuery(queryStr);
        q.setParameter("id", entidadeBasica.getId());
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