package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.model.Passagem;
import br.com.banav.model.PassagemHistorico;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by gustavocosta on 15/08/14.
 */
public class PassagemHistoricoDAO extends DAO<PassagemHistorico> {

    public List<PassagemHistorico> verificaPassagemHistorico(Passagem passagem){
        Query query = getEm().createQuery("select ph from PassagemHistorico ph where ph.passagem.id = :passagem");
        query.setParameter("passagem", passagem.getId());

        return query.getResultList();
    }




}
