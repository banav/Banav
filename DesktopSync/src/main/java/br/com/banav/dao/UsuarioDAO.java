package br.com.banav.dao;

import br.com.banav.dao.common.DAOLocalEntidadeBasica;
import br.com.banav.model.UsuarioLocal;
import br.com.banav.util.Util;

import javax.persistence.Query;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by GilsonRocha on 22/01/14.
 */
public class UsuarioDAO extends DAOLocalEntidadeBasica<UsuarioLocal> {

    public UsuarioLocal login(String login, String senha) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        senha = Util.toMD5(senha);

        Query query = getEM().createQuery("select u from UsuarioLocal u where u.login = :login and u.senha = :senha");
        query.setParameter("login", login);
        query.setParameter("senha", senha);

        List resultado = query.getResultList();
        return resultado == null || resultado.isEmpty() ? null : (UsuarioLocal) resultado.get(0);
    }


    @Override
    public void sincronizar(UsuarioLocal entidadeBasica) {

    }
}