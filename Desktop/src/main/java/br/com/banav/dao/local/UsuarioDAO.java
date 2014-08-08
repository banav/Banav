package br.com.banav.dao.local;

import br.com.banav.dao.common.DAOLocal;
import br.com.banav.dao.common.DAOLocalEntidadeBasica;
import br.com.banav.model.local.UsuarioLocal;
import br.com.banav.util.Util;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
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


}