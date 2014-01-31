package br.com.banav.service;

import br.com.banav.dao.ViagemDAO;
import br.com.banav.model.Frequencia;
import br.com.banav.model.Viagem;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Calendar;
import java.util.List;

/**
 * Created by GilsonRocha on 29/01/14.
 */
@Stateless
public class ViagemSrv {

    @Inject
    private ViagemDAO viagemDAO;

    public void salvar(Viagem viagem, Frequencia frequencia, Integer repeticoes) {
        viagemDAO.salvar(viagem);

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
            }
        }
    }

    public List<Viagem> listar() {
        return viagemDAO.listar();
    }
}
