package br.com.banav.service;

/**
 * Created by GilsonRocha on 22/01/14.
 */

import br.com.banav.dao.EnderecoDAO;
import br.com.banav.dao.UsuarioDAO;
import br.com.banav.model.Perfil;
import br.com.banav.model.Usuario;
import br.com.banav.util.Util;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
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
        try {
            usuario.setSenha(Util.toMD5(usuario.getSenha()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        usuarioDAO.atualizar(usuario);
    }

    public Usuario getUm(Long id) {
        return usuarioDAO.getUm(id, Usuario.class);
    }

    public List<Usuario> listar() {
        return usuarioDAO.listar();
    }

    public List<Usuario> listar(Long time){
        return usuarioDAO.listar(new Date(time));
    }

    public List<Usuario> listarVendedores(){
        return usuarioDAO.listarPor(Perfil.VENDEDOR);
    }

    public Usuario logar(Usuario usuario) {
        try {
            final List<Usuario> usuarios = usuarioDAO.listarPor(usuario.getLogin(), Util.toMD5(usuario.getSenha()));
            if(usuarios != null && !usuarios.isEmpty() && (usuarios.get(0).getPerfil().equals(Perfil.ADMINISTRADOR) || usuarios.get(0).getPerfil().equals(Perfil.SUPERADMIN))) {
                return usuarios.get(0);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Usuario getUsuarioByLogin(String login){
        return usuarioDAO.getUsuarioByLogin(login);
    }
}
