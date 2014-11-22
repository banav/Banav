package br.com.banav.jobs;

import br.com.banav.dao.PassagemCancelamentoDAO;
import br.com.banav.model.PassagemCancelamento;
import br.com.banav.ws.PassagemWS;

import java.util.Date;
import java.util.List;

/**
 * Created by gilson on 6/21/14.
 */
public class EnvioCancelamentoJob extends Thread {

    private PassagemCancelamentoDAO passagemCancelamentoDAO;

    private PassagemWS passagemWS;

    public EnvioCancelamentoJob() {
        setDaemon(true);
        setPriority(NORM_PRIORITY);

        passagemCancelamentoDAO = new PassagemCancelamentoDAO();
        passagemWS = new PassagemWS();
    }

    @Override
    public void run() {
        try {
            while(true) {
                sleep(10000);
                enviaCodigosPendentes();
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

    private void enviaCodigosPendentes() {
        List<PassagemCancelamento> naoEnviados = passagemCancelamentoDAO.listarNaoEnviados();
        for (PassagemCancelamento passagemCancelamento : naoEnviados) {
            Boolean enviado = enviaCodigo(passagemCancelamento.getCodigoBarras(), passagemCancelamento.getDataCadastro());

            if(enviado) {
                passagemCancelamento.setDataEnvio(new Date());
                passagemCancelamento.setEnviado(true);

                passagemCancelamentoDAO.atualizar(passagemCancelamento);
            }
        }
    }

    private Boolean enviaCodigo(String codigoBarras, Date dataCancelamento) {
        return passagemWS.cancelar(codigoBarras, dataCancelamento);
    }
}
