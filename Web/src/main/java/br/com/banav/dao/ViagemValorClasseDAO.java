package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.model.NavioClasse;
import br.com.banav.model.ViagemValorClasse;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by GilsonRocha on 29/01/14.
 */
public class ViagemValorClasseDAO extends DAO<ViagemValorClasse> {

    public List<ViagemValorClasse> listarPor(Long viagemId) {
        Query query = getEm().createQuery("select vvc from ViagemValorClasse as vvc where vvc.viagem.id = :id");
        query.setParameter("id", viagemId);

        return query.getResultList();
    }
}