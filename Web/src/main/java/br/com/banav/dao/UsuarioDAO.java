package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.model.Usuario;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by GilsonRocha on 22/01/14.
 */
public class UsuarioDAO extends DAO<Usuario> {

    public List<Usuario> listar() {
        Query query = getEm().createQuery("select u from Usuario as u order by u.nome");
        return query.getResultList();
    }
}