package br.com.banav.service;

import br.com.banav.dao.*;
import br.com.banav.model.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import java.util.Calendar;
import java.util.List;

/**
 * Created by GilsonRocha on 29/01/14.
 */
@Stateless
public class ViagemSrv {

    @Inject private ViagemDAO viagemDAO;

    @Inject private ViagemValorClasseDAO viagemValorClasseDAO;

    @Inject private NavioClasseDAO navioClasseDAO;

    @Inject private PortoDAO portoDAO;

    @Inject private NavioDAO navioDAO;

    public void salvar(Viagem viagem, Frequencia frequencia, Integer repeticoes, List<ViagemValorClasse> viagemValores) {
        Porto _origem = portoDAO.getUm(viagem.getOrigem().getId(), Porto.class);
        Porto _destino = portoDAO.getUm(viagem.getDestino().getId(), Porto.class);
        Navio _navio = navioDAO.getUm(viagem.getNavio().getNavioID(), Navio.class);

        viagem.setId(null);
        viagem.setDestino(_destino);
        viagem.setOrigem(_origem);
        viagem.setNavio(_navio);

        viagemDAO.salvar(viagem);

        salvarValorPassagens(viagem, viagemValores);

        if(!frequencia.equals(Frequencia.NUNCA)) {
            Calendar calendarSaida = Calendar.getInstance();
            calendarSaida.setTime(viagem.getHoraSaida());

            Calendar calendarChegada = Calendar.getInstance();
            calendarChegada.setTime(viagem.getHoraChegada());

            int tipoFrequencia = -1;

            if(frequencia.equals(Frequencia.DIARIAMENTE)) {
                tipoFrequencia = Calendar.DAY_OF_MONTH;
            } else if(frequencia.equals(Frequencia.SEMANALMENTE)) {
                tipoFrequencia = Calendar.WEEK_OF_MONTH;
            } else if(frequencia.equals(Frequencia.MENSALMENTE)) {
                tipoFrequencia = Calendar.MONTH;
            }

            for (int i = 0; i < repeticoes; i++) {
                calendarSaida.add(tipoFrequencia, 1);
                calendarChegada.add(tipoFrequencia, 1);

                Viagem novaViagem = new Viagem();
                novaViagem.setDestino(_destino);
                novaViagem.setOrigem(_origem);
                novaViagem.setDestino(_destino);
                novaViagem.setNavio(_navio);
                novaViagem.setHoraSaida(calendarSaida.getTime());
                novaViagem.setHoraChegada(calendarChegada.getTime());

                viagemDAO.salvar(novaViagem);

                salvarValorPassagens(novaViagem, viagemValores);
            }
        }
    }

    public void atualizar(Viagem viagem) {
        viagemDAO.atualizar(viagem);
    }

    public void remover(Long id) {
        viagemDAO.remover(id, Viagem.class);
    }

    public Viagem getUm(Long id) {
        return viagemDAO.getUm(id, Viagem.class);
    }

    public List<Viagem> listar() {
        return viagemDAO.listar();
    }

    public void salvarValorPassagens(Viagem viagem, List<ViagemValorClasse> viagemValores) {
        for (ViagemValorClasse _viagemValorClasse : viagemValores) {
            ViagemValorClasse viagemValorClasse = _viagemValorClasse.clone();
            viagemValorClasse.setViagem(viagem);

            NavioClasse navioClasse =  viagemValorClasse.getNavioClasse();

            Query q = viagemValorClasseDAO.getEm().createQuery("select nc from NavioClasse nc where nc.classe = :classe and nc.navio = :navio");
            q.setParameter("classe", navioClasse.getClasse());
            q.setParameter("navio", navioClasse.getNavio());
            navioClasse = (NavioClasse)q.getSingleResult();

            viagemValorClasse.setNavioClasse(navioClasse);

            if(viagemValorClasse.getId() == null) {
                viagemValorClasseDAO.salvar(viagemValorClasse);
            } else {
                viagemValorClasseDAO.atualizar(viagemValorClasse);
            }
        }
    }
}
