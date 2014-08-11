package br.com.banav.service;

import br.com.banav.dao.NavioClasseDAO;
import br.com.banav.model.NavioClasse;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by GilsonRocha on 27/01/14.
 */
@Stateless
public class NavioClasseSrv implements Serializable{

    @Inject
    private NavioClasseDAO navioClasseDAO;

    public List<NavioClasse> listarPor(Long navioId) {
        return navioClasseDAO.listarPor(navioId);
    }

    public List<NavioClasse> listar(Long time){
        return navioClasseDAO.listarPorDataMovimento(new Date(time), NavioClasse.class);
    }

    public List<NavioClasse> listar(){
        return navioClasseDAO.listar();
    }
}
