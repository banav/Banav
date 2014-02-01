package br.com.banav.service;

import br.com.banav.dao.EnderecoDAO;
import br.com.banav.dao.PortoDAO;
import br.com.banav.model.Cidade;
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

    public void salvar(Porto porto) {
        portoDAO.salvar(porto);
    }

    public void atualizar(Porto porto) {
        portoDAO.atualizar(porto);
    }

    public Porto getUm(Long id) {
        return portoDAO.getUm(id, Porto.class);
    }
}
