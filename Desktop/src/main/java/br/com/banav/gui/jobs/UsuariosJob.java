package br.com.banav.gui.jobs;

import br.com.banav.dao.UsuarioDAO;
import br.com.banav.model.UsuarioLocal;
import br.com.banav.ws.UsuarioDTO;
import br.com.banav.ws.UsuarioWS;

import java.util.Date;
import java.util.List;

/**
 * Created by gilson on 6/21/14.
 */
public class UsuariosJob extends Thread {

    private UsuarioDAO usuarioDAOLocal;

    private UsuarioWS usuarioWS;

    public UsuariosJob() {
        setDaemon(true);
        setPriority(NORM_PRIORITY);

        usuarioDAOLocal = new UsuarioDAO();
        usuarioWS = new UsuarioWS();
    }

    @Override
    public void run() {
        try {
            while(true) {
                sleep(60 * 1000);
                atualizarUsuarios();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void esperar() {
        interrupt();
    }

    public void retomar() {
        start();
    }

    private void atualizarUsuarios() {
        Date date = usuarioDAOLocal.ultimaAtualizacao(UsuarioLocal.class);
        List<UsuarioDTO> usuariosDTO = usuarioWS.listar(date);

        if(usuariosDTO == null)
            return;

        for (UsuarioDTO usuarioDTO : usuariosDTO) {
            UsuarioLocal usuarioLocal = usuarioDAOLocal.getUm(UsuarioLocal.class, usuarioDTO.getId());

            if(usuarioLocal == null) {
                usuarioLocal = new UsuarioLocal();
                usuarioLocal.setId(usuarioDTO.getId());
                usuarioLocal.setLogin(usuarioDTO.getLogin());
                usuarioLocal.setSenha(usuarioDTO.getSenha());
                usuarioLocal.setPerfil(usuarioDTO.getPerfil());
                usuarioLocal.setNome(usuarioDTO.getNome());
                usuarioLocal.setAtivo(usuarioDTO.getAtivo());
                usuarioLocal.setDataMovimentacao(usuarioDTO.getTime());

                usuarioDAOLocal.salvar(usuarioLocal);
            } else {
                usuarioLocal.setLogin(usuarioDTO.getLogin());
                usuarioLocal.setSenha(usuarioDTO.getSenha());
                usuarioLocal.setPerfil(usuarioDTO.getPerfil());
                usuarioLocal.setNome(usuarioDTO.getNome());
                usuarioLocal.setAtivo(usuarioDTO.getAtivo());
                usuarioLocal.setDataMovimentacao(usuarioDTO.getTime());

                usuarioDAOLocal.atualizar(usuarioLocal);
            }
        }
    }
}
