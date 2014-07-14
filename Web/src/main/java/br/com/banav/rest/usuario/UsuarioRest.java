package br.com.banav.rest.usuario;

import br.com.banav.model.Usuario;
import br.com.banav.service.UsuarioSrv;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavocosta on 14/07/14.
 */

@Path("/Usuario")
public class UsuarioRest implements Serializable {

    @Inject
    private UsuarioSrv usuarioSrv;



    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_XML)
    public List<UsuarioDTO> recuperarListaUsuario(){
        List<Usuario> usuarios = usuarioSrv.listar();

        List<UsuarioDTO> usuarioDTOs = new ArrayList<>();

        for(Usuario usuario : usuarios){
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(usuario.getId());
            usuarioDTO.setLogin(usuario.getLogin());
            usuarioDTO.setNome(usuario.getNome());
            usuarioDTO.setPerfil(usuario.getPerfil());
            usuarioDTO.setSenha(usuario.getSenha());
            usuarioDTOs.add(usuarioDTO);
        }

        return usuarioDTOs;
    }

}
