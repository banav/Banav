package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.dao.common.DAOLocalEntidadeBasica;
import br.com.banav.model.Classe;
import br.com.banav.model.Viagem;
import br.com.banav.model.ViagemValorClasse;

import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by GilsonRocha on 29/01/14.
 */
public class ViagemValorClasseDAO extends DAOLocalEntidadeBasica<ViagemValorClasse> {

    public ViagemValorClasse getPor(Viagem viagem, Classe classe) {
        Query query = getEM().createQuery("select vvc from ViagemValorClasse vvc where vvc.viagem.id = :id");
        query.setParameter("id", viagem.getId());

        List<ViagemValorClasse> lista = query.getResultList();
        for (ViagemValorClasse viagemValorClasse : lista) {
            if(viagemValorClasse.getNavioClasse().getClasse().equals(classe)) {
                return viagemValorClasse;
            }
        }

        return null;
    }

    @Override
    public void excluir(Class<ViagemValorClasse> clazz, Object id) {
        if(autoCommit) {
            getEM().getTransaction().begin();
            ViagemValorClasse viagemValorClasse = getUm(clazz, id);
            viagemValorClasse.setAtivo(false);

            getEM().getTransaction().commit();
        } else {
            ViagemValorClasse viagemValorClasse = getUm(clazz, id);
            viagemValorClasse.setAtivo(false);
        }
    }

    @Override
    public void sincronizar(ViagemValorClasse entidadeBasica) {

        String exists = "select count(1) from offline.viagem_valor_classe where id = :id";

        BigInteger count = (BigInteger) getEM().createNativeQuery(exists)
                .setParameter("id", entidadeBasica.getId())
                .getSingleResult();

        String strQuery = "";
        if(count.intValue() == 0){
            strQuery = "INSERT INTO offline.viagem_valor_classe(" +
                    "            id, valor, classe, navio, viagem_id, aceita_gratuidade, valor_meia, " +
                    "            ativo, datamovimentacao)" +
                    "    VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9)";
        }
        else{
            strQuery = "UPDATE offline.viagem_valor_classe" +
                    "   SET valor=?2, classe=?3, navio=?4, viagem_id=?5, aceita_gratuidade=?6, " +
                    "       valor_meia=?7, ativo=?8, datamovimentacao=?9" +
                    " WHERE id = ?1";
        }

        boolean isAlive = getEM().getTransaction().isActive();
        if(!isAlive) {
            getEM().getTransaction().begin();
        }

        Query query = getEM().createNativeQuery(strQuery);
        query.setParameter(1, entidadeBasica.getId());
        query.setParameter(2, entidadeBasica.getValor() == null ? 0 : entidadeBasica.getValor());
        query.setParameter(3, entidadeBasica.getNavioClasse().getClasse().getClasseID());
        query.setParameter(4, entidadeBasica.getNavioClasse().getNavio().getNavioID());
        query.setParameter(5, entidadeBasica.getViagem().getId());
        query.setParameter(6, entidadeBasica.getAceitaGratuidade());
        query.setParameter(7, entidadeBasica.getValorMeia() == null ? 0 : entidadeBasica.getValorMeia());
        query.setParameter(8, entidadeBasica.isAtivo());
        query.setParameter(9, entidadeBasica.getDataMovimentacao());

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
