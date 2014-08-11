package br.com.banav.gui.jobs;

import br.com.banav.dao.PassagemDAO;
import br.com.banav.gui.Passagem;
import br.com.banav.ws.PassagemWS;

import java.util.Date;
import java.util.List;

/**
 * Created by gilson on 6/21/14.
 */
public class EnvioPassagemJob extends Thread {

    private PassagemDAO passagemDAO;

    private PassagemWS passagemWS;

    public EnvioPassagemJob() {
        setDaemon(true);
        setPriority(NORM_PRIORITY);

        passagemDAO = new PassagemDAO();
        passagemWS = new PassagemWS();
    }

    @Override
    public void run() {
        try {
            while(true) {
                sleep(5000);
                enviaPassagensPendentes();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void enviaPassagensPendentes() {
        List<br.com.banav.model.Passagem> naoEnviados = passagemDAO.listarNaoEnviadas();
        for (br.com.banav.model.Passagem passagem : naoEnviados) {
            Boolean enviado = passagemWS.enviar(passagem.getCodigoBarras(), passagem.getViagemValorClasse().getId(), passagem.getGratuidade(), passagem.getValor(), passagem.getDataMovimentacao());

            if(enviado) {
                passagem.setEnviado(true);
                passagemDAO.atualizar(passagem);
            }
        }
    }
}
