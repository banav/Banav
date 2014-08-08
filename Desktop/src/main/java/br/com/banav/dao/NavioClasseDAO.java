package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.dao.common.DAOLocalEntidadeBasica;
import br.com.banav.model.Navio;
import br.com.banav.model.NavioClasse;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by gustavocosta on 07/02/14.
 */
public class NavioClasseDAO extends DAOLocalEntidadeBasica<NavioClasse> {


    public List<NavioClasse> listarPor(Navio navio) {
        Query query = getEM().createQuery("select nc from NavioClasse as nc where nc.navio.navioID = :idNavio");
        query.setParameter("idNavio", navio.getNavioID());

        return query.getResultList();
    }

    @Override
    public void sincronizar(NavioClasse entidadeBasica) {

        String count = "select count(1) from offline.navio_classe where classe = :classe and navio = :navio";

        Integer exists = (Integer) getEM().createNativeQuery(count)
                .setParameter("classe", entidadeBasica.getClasse().getClasseID())
                .setParameter("navio", entidadeBasica.getNavio().getNavioID())
                .getSingleResult();

        String query = "";

        if(exists.equals(0))
            query = "INSERT INTO offline.navio_classe(" +
                    "            classe, navio, quantidade, ativo, datamovimentacao)" +
                    "    VALUES (:classe, :navio, :quantidade, :ativo, :data)";
        else
            query = "UPDATE offline.navio_classe" +
                    "   SET quantidade=:quantidade, ativo=:ativo, datamovimentacao=:data" +
                    " WHERE classe=:classe and navio=:navio";

        Query q = getEM().createNativeQuery(query);
        q.setParameter("classe", entidadeBasica.getClasse().getClasseID());
        q.setParameter("navio", entidadeBasica.getNavio().getNavioID());
        q.setParameter("quantidade", entidadeBasica.getQuantidade());
        q.setParameter("ativo", entidadeBasica.isAtivo());
        q.setParameter("data", entidadeBasica.getDataMovimentacao());

        q.executeUpdate();
    }
}
