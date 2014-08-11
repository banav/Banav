package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.dao.common.DAOLocal;
import br.com.banav.model.Passagem;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by GilsonRocha on 27/01/14.
 */
public class PassagemDAO extends DAOLocal<Passagem> {

    public List<Passagem> listar() {
        Query query = getEM().createQuery("select p from Passagem as p order by p.id desc");
        return query.getResultList();
    }

    public List<Passagem> listarNaoEnviadas() {
        Query query = getEM().createQuery("select p from Passagem as p where p.enviado = false");
        return query.getResultList();
    }

    public List<Passagem> listarPorCodigoBarras(String codigoBarras) {Query query = getEM().createQuery("select p from Passagem as p where p.codigoBarras = :codigoBarras");
        query.setParameter("codigoBarras", codigoBarras);

        return query.getResultList();
    }

    public Integer nextval(Long viagemId) {
        StringBuffer sb = new StringBuffer();
        sb.append(" select count(*) from offline.passagem p where p.viagem_valor_classe_id in ( ");
        sb.append(" select vvc.id from offline.viagem_valor_classe vvc where vvc.viagem_id = :viagemId ");
        sb.append(" ) ");

        Query nativeQuery = getEM().createNativeQuery(sb.toString());
        nativeQuery.setParameter("viagemId", viagemId);

        Object singleResult = nativeQuery.getSingleResult();

        return Integer.parseInt(singleResult.toString()) + 1;
    }
}
