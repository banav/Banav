package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.model.Passagem;
import br.com.banav.model.PassagemHistorico;
import com.lowagie.text.pdf.BarcodeEAN;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by GilsonRocha on 27/01/14.
 */
public class PassagemDAO extends DAO<Passagem> {

    public List<Passagem> listar() {
        Query query = getEM().createQuery("select p from Passagem as p order by p.id desc");
        return query.getResultList();
    }

    public List<Passagem> listarPorCodigoBarras(String codigoBarras) {
        BarcodeEAN barcodeEAN = new BarcodeEAN();
        barcodeEAN.setCodeType(BarcodeEAN.EAN8);

        Query query = getEM().createQuery("select p from Passagem as p where p.codigoBarras = :codigoBarras");
        query.setParameter("codigoBarras", codigoBarras.substring(0,7));
        return query.getResultList();
    }
}
