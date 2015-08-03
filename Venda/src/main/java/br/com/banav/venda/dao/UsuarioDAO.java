package br.com.banav.venda.dao;

import br.com.banav.venda.dao.common.DAO;
import br.com.banav.venda.model.Perfil;
import br.com.banav.venda.model.Usuario;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by GilsonRocha on 22/01/14.
 */
public class UsuarioDAO extends DAO<Usuario> {

    public List<Usuario> listar() {
        Query query = getEm().createQuery("select u from Usuario as u order by u.nome");
        return query.getResultList();
    }

    public List<Usuario> listarPor(String login, String senha) {
        Query query = getEm().createQuery("select u from Usuario as u where u.login = :login and u.senha = :senha");
        query.setParameter("login", login);
        query.setParameter("senha", senha);

        return query.getResultList();
    }

    public List<Usuario> listar(Date time){

        Query query = getEm().createQuery("select u from Usuario u where u.dataMovimentacao >= :time");
        query.setParameter("time", time);
        return query.getResultList();

    }

    public List<Usuario> listarPor(Perfil perfil){
        Query query = getEm().createQuery("select u from Usuario u where u.perfil = :perfil");
        query.setParameter("perfil", Perfil.VENDEDOR);
        return query.getResultList();
    }
}