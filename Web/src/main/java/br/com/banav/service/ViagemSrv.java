package br.com.banav.service;

import br.com.banav.dao.NavioClasseDAO;
import br.com.banav.dao.ViagemDAO;
import br.com.banav.dao.ViagemValorClasseDAO;
import br.com.banav.model.Frequencia;
import br.com.banav.model.NavioClasse;
import br.com.banav.model.Viagem;
import br.com.banav.model.ViagemValorClasse;

import javax.ejb.Stateless;
import javax.inject.Inject;
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

    public void salvar(Viagem viagem, Frequencia frequencia, Integer repeticoes, List<ViagemValorClasse> viagemValores) {
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
                novaViagem.setDestino(viagem.getDestino());
                novaViagem.setOrigem(viagem.getOrigem());
                novaViagem.setNavio(viagem.getNavio());
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
        for (ViagemValorClasse viagemValorClasse : viagemValores) {
            viagemValorClasse.setViagem(viagem);

            if(viagemValorClasse.getId() == null) {
                viagemValorClasseDAO.salvar(viagemValorClasse);
            } else {
                viagemValorClasseDAO.atualizar(viagemValorClasse);
            }
        }
    }
}
