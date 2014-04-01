package br.com.banav.service;

/**
 * Created by GilsonRocha on 22/01/14.
 */

import br.com.banav.dao.EnderecoDAO;
import br.com.banav.dao.UsuarioDAO;
import br.com.banav.model.Usuario;
import br.com.banav.util.Util;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Stateless
public class UsuarioSrv {

    @Inject private UsuarioDAO usuarioDAO;

    @Inject private EnderecoDAO enderecoDAO;

    public void salvar(Usuario usuario) {
        try {
            usuario.setSenha(Util.toMD5(usuario.getSenha()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        usuarioDAO.salvar(usuario);
    }

    public void atualizar(Usuario usuario) {
        usuarioDAO.atualizar(usuario);
    }

    public Usuario getUm(Long id) {
        return usuarioDAO.getUm(id, Usuario.class);
    }

    public List<Usuario> listar() {
        return usuarioDAO.listar();
    }
}
