package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.dao.common.DAOLocalEntidadeBasica;
import br.com.banav.model.Classe;
import br.com.banav.model.Viagem;

import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by gustavocosta on 01/02/14.
 */
public class ClasseDAO extends DAOLocalEntidadeBasica<Classe> {

    public List<Classe> listar(){
        Query query = getEM().createQuery("select c from Classe c order by c.nome");
        return query.getResultList();
    }

    public List<Classe> getClassesMeiaPassagem(Viagem viagem) {
        Query query = getEM().createQuery("select vvc.navioClasse.classe from ViagemValorClasse vvc where vvc.viagem.id = :viagemId and vvc.valorMeia != null and vvc.valorMeia != 0");
        query.setParameter("viagemId", viagem.getId());

        return query.getResultList();
    }

    public List<Classe> getClassesGratuidade(Viagem viagem) {
        Query query = getEM().createQuery("select vvc.navioClasse.classe from ViagemValorClasse vvc where vvc.viagem.id = :viagemId and vvc.aceitaGratuidade = true");
        query.setParameter("viagemId", viagem.getId());

        return query.getResultList();
    }

    @Override
    public void sincronizar(Classe entidadeBasica) {

        String exists = "select count(1) from offline.classe where id = :id";

        BigInteger count = (BigInteger) getEM().createNativeQuery(exists)
                .setParameter("id", entidadeBasica.getClasseID())
                .getSingleResult();

        String queryStr = "";

        if(count.intValue() == 0){
            queryStr = "INSERT INTO offline.classe(" +
                    " id, nome, datamovimentacao, ativo) " +
                    " VALUES (:id, :nome, :data, :ativo)";
        }
        else{
            queryStr = "UPDATE offline.classe" +
                    " SET  nome= :nome, datamovimentacao= :data, ativo= :ativo" +
                    " WHERE id= :id";
        }

        boolean isAlive = getEM().getTransaction().isActive();
        if(!isAlive) {
            getEM().getTransaction().begin();
        }

        Query q = getEM().createNativeQuery(queryStr);
        q.setParameter("id", entidadeBasica.getClasseID());
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
