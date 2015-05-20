package br.com.banav.service;

import br.com.banav.dao.FaturamentoDAO;
import br.com.banav.model.dto.DataValorDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by gilson on 19/05/15.
 */
@Stateless
public class FaturamentoSrv {

    @Inject
    private FaturamentoDAO faturamentoDAO;

    public List<DataValorDTO> listarPor(Date inicio, Date fim) {
        return faturamentoDAO.listarPor(inicio, fim);
    }
}
