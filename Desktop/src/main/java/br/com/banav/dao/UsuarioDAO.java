package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.model.Usuario;
import br.com.banav.util.Util;

import javax.persistence.Query;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by GilsonRocha on 22/01/14.
 */
public class UsuarioDAO extends DAO<Usuario> {

    public List<Usuario> listar() {
        Query query = getEM().createQuery("select u from Usuario as u order by u.nome");
        return query.getResultList();
    }

    public Usuario login(String login, String senha) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        senha = Util.toMD5(senha);

        Query query = getEM().createQuery("select u from Usuario u where u.login = :login and u.senha = :senha");
        query.setParameter("login", login);
        query.setParameter("senha", senha);

        List resultado = query.getResultList();
        return resultado == null || resultado.isEmpty() ? null : (Usuario) resultado.get(0);
    }

    @Override
    public void excluir(Class<Usuario> clazz, Object id) {
        if(autoCommit) {
            getEM().getTransaction().begin();
            Usuario usu = getUm(clazz, id);
            usu.setAtivo(false);

            getEM().getTransaction().commit();
        } else {
            Usuario usu = getUm(clazz, id);
            usu.setAtivo(false);
        }
    }
}