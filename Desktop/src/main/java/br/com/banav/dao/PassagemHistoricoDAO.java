package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.gui.Passagem;
import br.com.banav.model.Navio;
import br.com.banav.model.PassagemHistorico;
import com.lowagie.text.pdf.BarcodeEAN;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by GilsonRocha on 27/01/14.
 */
public class PassagemHistoricoDAO extends DAO<PassagemHistorico> {

    public List<PassagemHistorico> listarPor(br.com.banav.model.Passagem passagem) {
        Query query = getEM().createQuery("select ph from PassagemHistorico as ph where ph.passagem.id = :id");
        query.setParameter("id", passagem.getId());
        return query.getResultList();
    }
}
