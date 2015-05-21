package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.model.Cidade;
import br.com.banav.model.Estado;
import br.com.banav.model.dto.DataValorDTO;

import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by gilson on 19/05/15.
 */
public class FaturamentoDAO extends DAO<DataValorDTO> {

    public List<DataValorDTO> listarPor(Date inicio, Date fim) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Query query = getEm().createNativeQuery("select\n" +
                "\tp.datavenda as data,\n" +
                "\tround(cast(sum(p.valor) as numeric), 2) as valor\n" +
                "\n" +
                "from\n" +
                "\tpassagem p,\n" +
                "\tviagem_valor_classe vvc,\n" +
                "\tclasse c,public.viagem v\n" +
                "where\n" +
                "\tvvc.id = p.viagem_valor_classe_id and\n" +
                "\tvvc.viagem_id = v.id and\n" +
                "\tp.datavenda between '" + simpleDateFormat.format(inicio) + " 00:00:00' and '" + simpleDateFormat.format(fim) + " 23:59:59' and\n" +
                "\tvvc.classe = c.id and\n" +
                "\tp.id not in (select ph.passagem_id from public.passagem_historico ph where ph.passagem_id = p.id and ph.passagemmovimento = 'CANCELADA')\n" +
                "GROUP by p.datavenda\n" +
                "ORDER BY p.datavenda", DataValorDTO.class);

        return query.getResultList();
    }
}
