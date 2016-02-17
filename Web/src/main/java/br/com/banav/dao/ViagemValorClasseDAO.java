package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.dao.common.DAOEntidadeBasica;
import br.com.banav.model.NavioClasse;
import br.com.banav.model.ViagemValorClasse;

import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Created by GilsonRocha on 29/01/14.
 */
public class ViagemValorClasseDAO extends DAOEntidadeBasica<ViagemValorClasse> {

    public List<ViagemValorClasse> listarPor(Long viagemId) {
        Query query = getEm().createQuery("select vvc from ViagemValorClasse as vvc where vvc.viagem.id = :id");
        query.setParameter("id", viagemId);

        return query.getResultList();
    }

    public List<ViagemValorClasse> listarPor(Long origem, Long destino, Date dia) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select vvc from ViagemValorClasse as vvc ");
        sql.append(" where vvc.viagem.origem.id = :origem ");
        sql.append(" and vvc.viagem.destino.id = :destino ");
        sql.append(" and date(vvc.viagem.horaSaida) = :dia ");
        sql.append(" order by vvc.viagem.horaSaida ");

        Query query = getEm().createQuery(sql.toString());
        query.setParameter("origem", origem);
        query.setParameter("destino", destino);
        query.setParameter("dia", dia, TemporalType.DATE);

        return query.getResultList();
    }

    public List<ViagemValorClasse> listar(){
        Query query = getEm().createQuery("select vvc from ViagemValorClasse vvc");
        return query.getResultList();
    }
}