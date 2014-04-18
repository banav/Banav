package br.com.banav.service;

import br.com.banav.dao.CortesiaDAO;
import br.com.banav.model.Cidade;
import br.com.banav.model.Cortesia;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by GilsonRocha on 27/01/14.
 */
@Stateless
public class CortesiaSrv {

    @Inject private CortesiaDAO cortesiaDAO;

    public List<Cortesia> listar() {
        return cortesiaDAO.listar();
    }

    public void salvar(Cortesia cortesia) {
        cortesiaDAO.salvar(cortesia);
    }

    public void atualizar(Cortesia cortesia) {
        cortesiaDAO.atualizar(cortesia);
    }

    public Cortesia getUm(Long id) {
        return cortesiaDAO.getUm(id, Cortesia.class);
    }
}