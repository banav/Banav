package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.dao.common.DAOLocalEntidadeBasica;
import br.com.banav.model.Usuario;
import br.com.banav.model.Viagem;

import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by GilsonRocha on 29/01/14.
 */
public class ViagemDAO extends DAOLocalEntidadeBasica<Viagem> {

    public List<Viagem> listar() {
        Query query = getEM().createQuery("select v from Viagem as v order by v.horaSaida desc");
        return query.getResultList();
    }

    public List<Viagem> listarPorDestino(Long destino, String data) {
        Query query = getEM().createQuery("select v from Viagem v where v.destino.id = :destino and to_char(v.horaSaida, 'DD/MM/YYYY') = :data order by v.horaSaida desc");
        query.setParameter("destino", destino);
        query.setParameter("data", data);

        return query.getResultList();
    }

    @Override
    public void excluir(Class<Viagem> clazz, Object id) {
        if(autoCommit) {
            getEM().getTransaction().begin();
            Viagem viagem = getUm(clazz, id);
            viagem.setAtivo(false);

            getEM().getTransaction().commit();
        } else {
            Viagem viagem = getUm(clazz, id);
            viagem.setAtivo(false);
        }
    }

    @Override
    public void sincronizar(Viagem entidadeBasica) {
        String exists = "select count(1) from offline.viagem where id = :id";

        BigInteger count = (BigInteger) getEM().createNativeQuery(exists)
                .setParameter("id", entidadeBasica.getId())
                .getSingleResult();

        String queryStr = "";

        if(count.intValue() == 0){
            queryStr = "INSERT INTO offline.viagem(" +
                    "            id, horachegada, horasaida, porto_destino, navio_id, porto_origem, " +
                    "            ativo, datamovimentacao)" +
                    "    VALUES (:id, :horachegada, :horasaida, :destino, :navio, :origem, :ativo, :datamovimentacao)";
        }
        else{
            queryStr = "UPDATE offline.viagem" +
                    "   SET horachegada=:horachegada, horasaida=:horasaida, porto_destino=:destino, navio_id=:navio, " +
                    "       porto_origem=:origem, ativo=:ativo, datamovimentacao=:datamovimentacao" +
                    " WHERE id = :id";
        }

        getEM().getTransaction().begin();

        Query q = getEM().createNativeQuery(queryStr);
        q.setParameter("id", entidadeBasica.getId());
        q.setParameter("horachegada", entidadeBasica.getHoraChegada());
        q.setParameter("horasaida", entidadeBasica.getHoraSaida());
        q.setParameter("destino", entidadeBasica.getDestino());
        q.setParameter("navio", entidadeBasica.getNavio());
        q.setParameter("origem", entidadeBasica.getOrigem());
        q.setParameter("ativo", entidadeBasica.isAtivo());
        q.setParameter("datamovimentacao", entidadeBasica.getDataMovimentacao());

        try{
            q.executeUpdate();
            getEM().getTransaction().commit();
        }catch (Exception ex){
            getEM().getTransaction().rollback();
        }
    }
}
