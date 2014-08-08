package br.com.banav.service;

import br.com.banav.dao.ClasseDAO;
import br.com.banav.dao.NavioClasseDAO;
import br.com.banav.dao.NavioDAO;
import br.com.banav.model.Classe;
import br.com.banav.model.Navio;
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
public class NavioSrv implements Serializable{

    @Inject private NavioDAO navioDAO;

    @Inject
    private NavioClasseDAO navioClasseDAO;

    @Inject
    private ClasseDAO classeDAO;

    public List<Navio> listar() {
        return navioDAO.listar();
    }

    public void salvar(Navio navio) {
        List<NavioClasse> navioclasses = navio.getClasses();

        for(NavioClasse navioClasse : navioclasses){
            Classe classe = classeDAO.getUm(navioClasse.getClasse().getClasseID(), Classe.class);
            navioClasse.setClasse(classe);
        }
        navioDAO.salvar(navio);
    }

    public void atualizar(Navio navio) {
        navioDAO.atualizar(navio);
    }

    public Navio getUm(Long id) {
        return navioDAO.getUm(id, Navio.class);
    }

    public Navio getUmComClasses(Long id){

        Navio navio = navioDAO.getUmComClasse(id);

        return navio;
    }


    public void salvarNavioClasse(NavioClasse navioClasse){
        navioClasseDAO.salvar(navioClasse);
    }

    public List<Navio> listar(Long time){
        return navioDAO.listarPorDataMovimento(new Date(time), Navio.class);
    }
}