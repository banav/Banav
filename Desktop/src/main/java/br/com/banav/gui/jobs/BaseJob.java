package br.com.banav.gui.jobs;

import br.com.banav.dao.ClasseDAO;
import br.com.banav.dao.NavioDAO;
import br.com.banav.dao.PortoDAO;
import br.com.banav.dao.local.UsuarioDAO;
import br.com.banav.model.Classe;
import br.com.banav.model.Navio;
import br.com.banav.model.Porto;
import br.com.banav.model.local.UsuarioLocal;
import br.com.banav.ws.*;
import br.com.banav.ws.dto.ClasseDTO;
import br.com.banav.ws.dto.NavioDTO;
import br.com.banav.ws.dto.PortoDTO;

import java.util.Date;
import java.util.List;

/**
 * Created by gilson on 6/21/14.
 */
public class BaseJob extends Thread {

    private NavioDAO navioDAOLocal;

    private NavioWS navioWS;

    private ClasseDAO classeDAO;

    private ClasseWS classeWS;

    private PortoDAO portoDAO;

    private PortoWS portoWS;

    public BaseJob() {
        setDaemon(true);
        setPriority(NORM_PRIORITY);

        navioDAOLocal = new NavioDAO();
        navioWS = new NavioWS();

        classeDAO = new ClasseDAO();
        classeWS = new ClasseWS();

        portoDAO = new PortoDAO();
        portoWS = new PortoWS();
    }

    @Override
    public void run() {
        try {
            while(true) {
                sleep(60 * 1000);
                atualizarNavios();
                atualizaClasses();
                atualizaPortos();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void atualizarNavios() {
        Date date = navioDAOLocal.ultimaAtualizacao(Navio.class);
        List<NavioDTO> naviosDTO = navioWS.listar(date);
        for (NavioDTO navioDTO : naviosDTO) {

            Navio navio = new Navio();
            navio.setNome(navioDTO.getNome());
            navio.setAtivo(navioDTO.getAtivo());
            navio.setDataMovimentacao(navioDTO.getDataMovimentacao());
            navio.setNavioID(navioDTO.getId());

            navioDAOLocal.sincronizar(navio);


        }
    }

    private void atualizaClasses(){
        Date date = classeDAO.ultimaAtualizacao(Classe.class);
        List<ClasseDTO> classesDTO = classeWS.listar(date);

        for(ClasseDTO classeDTO : classesDTO){

            Classe classe = new Classe();
            classe.setNome(classeDTO.getNome());
            classe.setAtivo(classeDTO.getAtivo());
            classe.setDataMovimentacao(classeDTO.getDataMovimentacao());
            classe.setClasseID(classeDTO.getId());

            classeDAO.sincronizar(classe);
        }
    }

    private void atualizaPortos(){
        Date date = portoDAO.ultimaAtualizacao(Porto.class);
        List<PortoDTO> portosDTO = portoWS.listar(date);

        for(PortoDTO portoDTO : portosDTO){
            Porto porto = new Porto();
            porto.setNome(portoDTO.getNome());
            porto.setId(portoDTO.getId());
            porto.setDataMovimentacao(portoDTO.getDataMovimentacao());
            porto.setAtivo(portoDTO.getAtivo());

            portoDAO.sincronizar(porto);
        }

    }
}
