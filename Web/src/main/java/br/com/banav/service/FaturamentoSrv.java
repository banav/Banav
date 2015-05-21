package br.com.banav.service;

import br.com.banav.dao.FaturamentoDAO;
import br.com.banav.model.dto.DataValorDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by gilson on 19/05/15.
 */
@Stateless
public class FaturamentoSrv {

    @Inject
    private FaturamentoDAO faturamentoDAO;

    public List<DataValorDTO> listarPor(Integer mes, Integer ano) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Calendar dataInicial = Calendar.getInstance();
        dataInicial.set(ano, mes, 1);

        Calendar dataFinal = Calendar.getInstance();
        dataFinal.setTime(dataInicial.getTime());
        dataFinal.set(Calendar.DAY_OF_MONTH, dataFinal.getActualMaximum(Calendar.DAY_OF_MONTH));

        return faturamentoDAO.listarPor(dataInicial.getTime(), dataFinal.getTime());
    }
}
