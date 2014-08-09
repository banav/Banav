package br.com.banav.dao.local;

import br.com.banav.dao.common.DAOLocal;
import br.com.banav.model.local.CheckIn;

import javax.persistence.Query;
import java.util.Calendar;
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

    public List<CheckIn> listarPor(String codigo) {
        Query query = getEM().createQuery("select c from CheckIn as c where c.codigoBarras = :codigo");
        query.setParameter("codigo", codigo);
        return query.getResultList();
    }

    public Boolean isValido(String codigoBarras) {
        //Ano -        14
        //Mes -        06
        //Dia -        20
        //Origem -     01
        //Destino -    02
        //Sequencial - 0001
        String anoCodigo = codigoBarras.substring(0,2);
        String mesCodigo = codigoBarras.substring(2, 4);
        String diaCodigo = codigoBarras.substring(4, 6);

        Calendar hoje = Calendar.getInstance();
        String ano = Integer.toString(hoje.get(Calendar.YEAR)).substring(2);
        String mes = String.format("%02d", hoje.get(Calendar.MONTH) + 1);
        String dia = String.format("%02d", hoje.get(Calendar.DAY_OF_MONTH));

        if(!ano.equals(anoCodigo)) {
            return false;
        }

        if(!mes.equals(mesCodigo)) {
            return false;
        }

        if(!dia.equals(diaCodigo)) {
            return false;
        }

        return true;
    }
}
