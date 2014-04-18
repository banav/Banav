package br.com.banav.service;

import br.com.banav.dao.TipoGratuidadeDAO;
import br.com.banav.model.TipoGratuidade;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by GilsonRocha on 27/01/14.
 */
@Stateless
public class TipoGratuidadeSrv {

    @Inject private TipoGratuidadeDAO tipoGratuidadeDAO;

    public List<TipoGratuidade> listar() {
        return tipoGratuidadeDAO.listar();
    }

    public void salvar(TipoGratuidade tipoGratuidade) {
        tipoGratuidadeDAO.salvar(tipoGratuidade);
    }

    public void atualizar(TipoGratuidade tipoGratuidade) {
        tipoGratuidadeDAO.atualizar(tipoGratuidade);
    }

    public TipoGratuidade getUm(Long id) {
        return tipoGratuidadeDAO.getUm(id, TipoGratuidade.class);
    }
}
