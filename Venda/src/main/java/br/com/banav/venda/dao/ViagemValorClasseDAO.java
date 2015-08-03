package br.com.banav.venda.dao;

import br.com.banav.venda.dao.common.DAO;
import br.com.banav.venda.model.ViagemValorClasse;

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

    public List<ViagemValorClasse> listar(){
        Query query = getEm().createQuery("select vvc from ViagemValorClasse vvc");
        return query.getResultList();
    }


}