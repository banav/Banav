package br.com.banav.service;

import br.com.banav.dao.NavioClasseDAO;
import br.com.banav.dao.ViagemValorClasseDAO;
import br.com.banav.model.Navio;
import br.com.banav.model.NavioClasse;
import br.com.banav.model.Viagem;
import br.com.banav.model.ViagemValorClasse;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by GilsonRocha on 27/01/14.
 */
@Stateless
public class ViagemValorClasseSrv implements Serializable {

    @Inject private NavioClasseDAO navioClasseDAO;

    @Inject private ViagemValorClasseDAO viagemValorClasseDAO;

    public List<ViagemValorClasse> listarPor(Navio navio) {
        List<ViagemValorClasse> viagemValorClasses = new ArrayList<ViagemValorClasse>();

        final List<NavioClasse> navioClasses = navioClasseDAO.listarPor(navio.getNavioID());
        for (NavioClasse navioClasse : navioClasses) {
            ViagemValorClasse viagemValorClasse = new ViagemValorClasse();
            viagemValorClasse.setNavioClasse(navioClasse);

            viagemValorClasses.add(viagemValorClasse);
        }

        return viagemValorClasses;
    }

    public List<ViagemValorClasse> listarPor(Viagem viagem) {
        return viagemValorClasseDAO.listarPor(viagem.getId());
    }

    public List<ViagemValorClasse> listar(Long time){
        return viagemValorClasseDAO.listarPorDataMovimento(new Date(time), ViagemValorClasse.class);
    }

    public List<ViagemValorClasse> listar(){
        return viagemValorClasseDAO.listar();
    }

    public void atualizar(ViagemValorClasse viagemValorClasse){
        ViagemValorClasse _viagemValorClasse = viagemValorClasseDAO.getUm(viagemValorClasse.getId(), ViagemValorClasse.class);
        viagemValorClasse.setNavioClasse(_viagemValorClasse.getNavioClasse());
        viagemValorClasse.setViagem(_viagemValorClasse.getViagem());
        viagemValorClasseDAO.atualizar(viagemValorClasse);
    }

    public ViagemValorClasse getUm(Long id){
        return viagemValorClasseDAO.getUm(id, ViagemValorClasse.class);
    }
}
