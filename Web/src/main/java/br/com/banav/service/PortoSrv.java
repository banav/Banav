package br.com.banav.service;

import br.com.banav.dao.PortoDAO;
import br.com.banav.model.Porto;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by GilsonRocha on 27/01/14.
 */
@Stateless
public class PortoSrv {

    @Inject private PortoDAO portoDAO;

    public List<Porto> listar() {
        return portoDAO.listar();
    }
}
