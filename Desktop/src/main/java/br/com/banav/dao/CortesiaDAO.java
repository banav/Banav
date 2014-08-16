package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.dao.common.DAOLocalEntidadeBasica;
import br.com.banav.model.Cortesia;
import br.com.banav.model.Passagem;

import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by GilsonRocha on 27/01/14.
 */
public class CortesiaDAO extends DAOLocalEntidadeBasica<Cortesia> {



    public List<Cortesia> listar() {
        Query query = getEM().createQuery("select c from Cortesia as c where c.passagem is null order by c.nome");
        return query.getResultList();
    }

    @Override
    public void sincronizar(Cortesia entidadeBasica) {



        String exists = "select count(1) from offline.cortesia where id = :id";

        BigInteger count = (BigInteger) getEM().createNativeQuery(exists)
                .setParameter("id", entidadeBasica.getId())
                .getSingleResult();

        String queryStr = "";

        if(count.intValue() == 0){
            queryStr = "INSERT INTO offline.cortesia(" +
                    "            id, autorizante, cpf, data_criacao, nome, rg, solicitante, ativo, " +
                    "            usuario_id, viagem_id, datamovimentacao";

            if(entidadeBasica.getPassagem() != null)
                queryStr = queryStr + ", passagem_id";

            if(entidadeBasica.getPassagem() != null)
                queryStr = queryStr +")    VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12)";
            else
                queryStr = queryStr +")    VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11)";

        }
        else{
            queryStr = "UPDATE cortesia" +
                    "   SET autorizante=?2, cpf=?3, data_criacao=?4, nome=?5, rg=?6, solicitante=?7, " +
                    "       ativo=?8, usuario_id=?9, viagem_id=?10, datamovimentacao=?11, ";
            if(entidadeBasica.getPassagem() != null)
                queryStr = queryStr + " passagem_id =?12";

            queryStr = queryStr +" WHERE id = ?1";

        }

        boolean isAlive = getEM().getTransaction().isActive();
        if(!isAlive) {
            getEM().getTransaction().begin();
        }

        Query query = getEM().createNativeQuery(queryStr);
        query.setParameter(1, entidadeBasica.getId());
        query.setParameter(2,entidadeBasica.getAutorizante());
        query.setParameter(3, entidadeBasica.getCpf());
        query.setParameter(4, entidadeBasica.getDataCriacao());
        query.setParameter(5, entidadeBasica.getNome());
        query.setParameter(6, entidadeBasica.getRg());
        query.setParameter(7, entidadeBasica.getSolicitante());
        query.setParameter(8, entidadeBasica.isAtivo());
        query.setParameter(9, entidadeBasica.getUsuario().getId());
        query.setParameter(10, entidadeBasica.getViagem().getId());
        query.setParameter(11, entidadeBasica.getDataMovimentacao());

        if(entidadeBasica.getPassagem() != null){

            PassagemDAO passagemDAO = new PassagemDAO();
            Passagem passagem = passagemDAO.buscarPorCodigoBarras(entidadeBasica.getPassagem().getCodigoBarras());

            query.setParameter(12, passagem.getId());
        }
        try{
            query.executeUpdate();
            if(!isAlive) {
                getEM().getTransaction().commit();
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            if(!isAlive) {
                getEM().getTransaction().rollback();
            }
        }
    }
}
