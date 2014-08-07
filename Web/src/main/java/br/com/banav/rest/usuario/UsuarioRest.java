package br.com.banav.rest.usuario;

import br.com.banav.model.Usuario;
import br.com.banav.service.UsuarioSrv;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavocosta on 14/07/14.
 */

@Path("/usuario")
public class UsuarioRest implements Serializable {

    @Inject
    private UsuarioSrv usuarioSrv;



    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_XML)
    public List<UsuarioDTO> recuperarListaUsuario(){
        List<Usuario> usuarios = usuarioSrv.listar();

        List<UsuarioDTO> usuarioDTOs = new ArrayList<UsuarioDTO>();

        for(Usuario usuario : usuarios){
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(usuario.getId());
            usuarioDTO.setLogin(usuario.getLogin());
            usuarioDTO.setNome(usuario.getNome());
            usuarioDTO.setPerfil(usuario.getPerfil());
            usuarioDTO.setSenha(usuario.getSenha());
            usuarioDTO.setTime(usuario.getDataMovimentacao());
            usuarioDTO.setAtivo(usuario.isAtivo());
            usuarioDTOs.add(usuarioDTO);
        }

        return usuarioDTOs;
    }

    @GET
    @Path("/list/{time}")
    @Produces(MediaType.APPLICATION_XML)
    public List<UsuarioDTO> recuperarListaUsuario(@PathParam("time") Long time){
        List<Usuario> usuarios = usuarioSrv.listar(time);

        List<UsuarioDTO> usuarioDTOs = new ArrayList<UsuarioDTO>();

        for(Usuario usuario : usuarios){
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(usuario.getId());
            usuarioDTO.setLogin(usuario.getLogin());
            usuarioDTO.setNome(usuario.getNome());
            usuarioDTO.setPerfil(usuario.getPerfil());
            usuarioDTO.setSenha(usuario.getSenha());
            usuarioDTO.setTime(usuario.getDataMovimentacao());
            usuarioDTO.setAtivo(usuario.isAtivo());
            usuarioDTOs.add(usuarioDTO);
        }

        return usuarioDTOs;
    }

}
