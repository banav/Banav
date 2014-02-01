package br.com.banav.service;

import br.com.banav.dao.CidadeDAO;
import br.com.banav.model.Cidade;
import br.com.banav.model.Estado;
import br.com.banav.model.Usuario;

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

    public void salvar(Cidade cidade) {
        cidadeDAO.salvar(cidade);
    }

    public void atualizar(Cidade cidade) {
        cidadeDAO.atualizar(cidade);
    }

    public Cidade getUm(Long id) {
        return cidadeDAO.getUm(id, Cidade.class);
    }

    public List<Estado> listarEstado(){
        return cidadeDAO.listarEstado();
    }
}
