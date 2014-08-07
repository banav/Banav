package br.com.banav.service;

import br.com.banav.dao.ClasseDAO;
import br.com.banav.model.Cidade;
import br.com.banav.model.Classe;
import br.com.banav.rest.dto.ClasseDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by gustavocosta on 01/02/14.
 */
@Stateless
public class ClasseSrv implements Serializable{

    @Inject
    private ClasseDAO classeDAO;

    public List<Classe> listar(){
        return classeDAO.listar();
    }


    public void salvar(Classe classe) {
        classeDAO.salvar(classe);
    }

    public void atualizar(Classe classe) {
        classeDAO.atualizar(classe);
    }

    public Classe getUm(Long id) {
        return classeDAO.getUm(id, Classe.class);
    }

    public List<Classe> listar (Long time){
        return classeDAO.listarPorDataMovimento(new Date(time), Classe.class);
    }

}
