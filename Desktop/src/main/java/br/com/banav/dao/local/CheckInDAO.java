package br.com.banav.dao.local;

import br.com.banav.dao.common.DAOLocal;
import br.com.banav.model.Cidade;
import br.com.banav.model.local.CheckIn;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by gilson on 6/21/14.
 */
public class CheckInDAO extends DAOLocal<CheckIn> {

    public List<CheckIn> listarNaoEnviados() {
        Query query = getEM().createQuery("select c from CheckIn as c where c.enviado = false");
        query.setMaxResults(5);
        return query.getResultList();
    }
}
