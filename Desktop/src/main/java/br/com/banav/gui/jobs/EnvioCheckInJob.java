package br.com.banav.gui.jobs;

import br.com.banav.dao.local.CheckInDAO;
import br.com.banav.model.local.CheckIn;
import br.com.banav.ws.CheckInWS;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by gilson on 6/21/14.
 */
public class EnvioCheckInJob extends Thread {

    private CheckInDAO checkInDAO;

    private CheckInWS checkInWS;

    public EnvioCheckInJob() {
        setDaemon(true);
        setPriority(NORM_PRIORITY);

        checkInDAO = new CheckInDAO();
        checkInWS = new CheckInWS();
    }

    @Override
    public void run() {
        try {
            while(true) {
                sleep(3000);
                enviaCodigosPendentes();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void enviaCodigosPendentes() {
        List<CheckIn> naoEnviados = checkInDAO.listarNaoEnviados();
        for (CheckIn checkIn : naoEnviados) {
            Boolean enviado = enviaCodigo(checkIn.getCodigoBarras());

            if(enviado) {
                checkIn.setDataEnvio(new Date());
                checkIn.setEnviado(true);

                checkInDAO.atualizar(checkIn);
            }
        }
    }

    private Boolean enviaCodigo(String codigoBarras) {
        try {
            return checkInWS.enviar(codigoBarras);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
