package br.com.banav.service;

import br.com.banav.dao.NavioDAO;
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
}