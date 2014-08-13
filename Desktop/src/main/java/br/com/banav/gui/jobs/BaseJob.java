package br.com.banav.gui.jobs;

import br.com.banav.dao.*;
import br.com.banav.dao.local.UsuarioDAO;
import br.com.banav.model.*;
import br.com.banav.model.local.UsuarioLocal;
import br.com.banav.rest.dto.ViagemValorClasseDTO;
import br.com.banav.ws.*;
import br.com.banav.ws.dto.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

/**
 * Created by gilson on 6/21/14.
 */
public class BaseJob extends Thread {

    private static EntityManagerFactory emf;

    private static EntityManager em;
    static{


        if(emf == null) {
            emf = Persistence.createEntityManagerFactory("BanavLocalPU");
        }

        if(em == null) {
            em = emf.createEntityManager();
        }
    }

    private NavioDAO navioDAOLocal;

    private NavioWS navioWS;

    private ClasseDAO classeDAO;

    private ClasseWS classeWS;

    private PortoDAO portoDAO;

    private PortoWS portoWS;

    private NavioClasseDAO navioClasseDAO;

    private NavioClasseWS navioClasseWS;

    private ViagemDAO viagemDAO;

    private ViagemWS viagemWS;

    private ViagemValorClasseDAO viagemValorClasseDAO;

    private ViagemValorClasseWS viagemValorClasseWS;

    public BaseJob() {
        setDaemon(true);
        setPriority(NORM_PRIORITY);

        navioDAOLocal = new NavioDAO();
        navioDAOLocal.setEM(em);
        navioWS = new NavioWS();

        classeDAO = new ClasseDAO();
        classeWS = new ClasseWS();

        portoDAO = new PortoDAO();
        portoWS = new PortoWS();

        navioClasseDAO = new NavioClasseDAO();
        navioClasseWS = new NavioClasseWS();

        viagemDAO = new ViagemDAO();
        viagemWS = new ViagemWS();

        viagemValorClasseDAO = new ViagemValorClasseDAO();
        viagemValorClasseWS = new ViagemValorClasseWS();


    }

    @Override
    public void run() {
        try {
            while(true) {
                sleep(7000);
                atualizarNavios();
                atualizaClasses();
                atualizaPortos();
                atualizaNavioClasse();
                atualizaViagem();
                atualizaViagemValorClasse();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void atualizarNavios() {
        Date date = navioDAOLocal.ultimaAtualizacao(Navio.class);
        List<NavioDTO> naviosDTO = navioWS.listar(date);

        if (naviosDTO == null)
            return;

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

        if(classesDTO == null)
            return;

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

        if (portosDTO == null)
            return;

        for(PortoDTO portoDTO : portosDTO){
            Porto porto = new Porto();
            porto.setNome(portoDTO.getNome());
            porto.setId(portoDTO.getId());
            porto.setDataMovimentacao(portoDTO.getDataMovimentacao());
            porto.setAtivo(portoDTO.getAtivo());

            portoDAO.sincronizar(porto);
        }

    }

    private void atualizaNavioClasse(){
        Date date = navioClasseDAO.ultimaAtualizacao(NavioClasse.class);

        List<NavioClasseDTO> navioClasseDTOs = navioClasseWS.listar(date);

        if(navioClasseDTOs == null)
            return;

        for(NavioClasseDTO dto : navioClasseDTOs){
            NavioClasse navioClasse = new NavioClasse();

            Classe classe = new Classe();
            classe.setClasseID(dto.getClasseID());
            navioClasse.setClasse(classe);

            Navio navio = new Navio();
            navio.setNavioID(dto.getNavioID());
            navioClasse.setNavio(navio);

            navioClasse.setDataMovimentacao(dto.getDataMovimentacao());
            navioClasse.setAtivo(dto.getAtivo());
            navioClasse.setQuantidade(dto.getQuantidade());

            navioClasseDAO.sincronizar(navioClasse);
        }
    }

    private void atualizaViagem(){
        Date date = viagemDAO.ultimaAtualizacao(Viagem.class);

        List<ViagemDTO> viagemDTOs = viagemWS.listar(date);

        if( viagemDTOs == null)
            return;

        for(ViagemDTO dto : viagemDTOs){
            Viagem viagem = new Viagem();
            viagem.setId(dto.getId());
            viagem.setDataMovimentacao(dto.getDataMovimentacao());
            viagem.setAtivo(dto.getAtivo());

            Navio navio = new Navio();
            navio.setNavioID(dto.getNavioID());
            viagem.setNavio(navio);

            Porto destino = new Porto();
            destino.setId(dto.getDestinoID());
            viagem.setDestino(destino);


            viagem.setHoraChegada(dto.getHoraChegada());
            viagem.setHoraSaida(dto.getHoraSaida());

            Porto origem = new Porto();
            origem.setId(dto.getOrigemID());
            viagem.setOrigem(origem);

            viagemDAO.sincronizar(viagem);

        }
    }

    private void atualizaViagemValorClasse(){
        Date date = viagemValorClasseDAO.ultimaAtualizacao(ViagemValorClasse.class);

        List<ViagemValorClasseDTO> viagemValorClasseDTOs = viagemValorClasseWS.listar(date);

        if( viagemValorClasseDTOs == null)
            return;

        for(ViagemValorClasseDTO dto : viagemValorClasseDTOs){
            ViagemValorClasse viagemValorClasse = new ViagemValorClasse();

            viagemValorClasse.setId(dto.getId());
            viagemValorClasse.setDataMovimentacao(dto.getDataMovimentacao());
            viagemValorClasse.setAtivo(dto.getAtivo());
            viagemValorClasse.setValorMeia(dto.getValorMeia());
            viagemValorClasse.setValor(dto.getValor());
            viagemValorClasse.setAceitaGratuidade(dto.getAceitaGratuidade());

            Viagem viagem = new Viagem();
            viagem.setId(dto.getViagemID());
            viagemValorClasse.setViagem(viagem);

            NavioClasse navioClasse = new NavioClasse();

            Navio navio = new Navio();
            navio.setNavioID(dto.getNavioID());
            navioClasse.setNavio(navio);

            Classe classe = new Classe();
            classe.setClasseID(dto.getClasseID());
            navioClasse.setClasse(classe);
            viagemValorClasse.setNavioClasse(navioClasse);

            viagemValorClasseDAO.sincronizar(viagemValorClasse);

        }
    }
}
