package br.com.banav.dao;

import br.com.banav.dao.common.DAOLocal;
import br.com.banav.model.PassagemCancelamento;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by gilson on 6/21/14.
 */
public class PassagemCancelamentoDAO extends DAOLocal<PassagemCancelamento> {

    public List<PassagemCancelamento> listarNaoEnviados() {
        Query query = getEM().createQuery("select pc from PassagemCancelamento as pc, Passagem  p " +
                "where p.codigoBarras = pc.codigoBarras and p.enviado = true and pc.enviado = false");
        return query.getResultList();
    }

    public List<PassagemCancelamento> listarPor(String codigo) {
        Query query = getEM().createQuery("select pc from PassagemCancelamento as pc where pc.codigoBarras = :codigo");
        query.setParameter("codigo", codigo);
        return query.getResultList();
    }
}
