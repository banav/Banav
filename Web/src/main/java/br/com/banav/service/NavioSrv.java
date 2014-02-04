package br.com.banav.service;

import br.com.banav.dao.NavioDAO;
import br.com.banav.model.Classe;
import br.com.banav.model.Navio;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by GilsonRocha on 27/01/14.
 */
@Stateless
public class NavioSrv {

    @Inject private NavioDAO navioDAO;

    public List<Navio> listar() {
        return navioDAO.listar();
    }

    public void salvar(Navio navio) {
        navioDAO.salvar(navio);
    }

    public void atualizar(Navio navio) {
        navioDAO.atualizar(navio);
    }

    public Navio getUm(Long id) {
        return navioDAO.getUm(id, Navio.class);
    }
}
