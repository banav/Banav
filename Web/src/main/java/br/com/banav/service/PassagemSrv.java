package br.com.banav.service;

import br.com.banav.dao.PassagemDAO;
import br.com.banav.model.Passagem;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by GilsonRocha on 27/01/14.
 */
@Stateless
public class PassagemSrv {

    @Inject private PassagemDAO passagemDAO;


    public List<Passagem> listar() {
        return passagemDAO.listar();
    }
}
