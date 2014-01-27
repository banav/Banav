package br.com.banav.service;

import br.com.banav.dao.CidadeDAO;
import br.com.banav.model.Cidade;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by GilsonRocha on 27/01/14.
 */
@Stateless
public class CidadeSrv {

    @Inject private CidadeDAO cidadeDAO;

    public List<Cidade> listar() {
        return cidadeDAO.listar();
    }
}
