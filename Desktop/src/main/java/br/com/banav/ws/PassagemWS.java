package br.com.banav.ws;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by gilson on 6/24/14.
 */
public class PassagemWS extends RestClient {

    private static FileHandler fileTxt;

    private final static Logger LOGGER = Logger.getLogger(PassagemWS.class.getName());

    public PassagemWS() {
        Calendar hoje = Calendar.getInstance();
        String logFile = String.format("%s-%s-%s-passagem-ws.txt", hoje.get(Calendar.DAY_OF_MONTH), hoje.get(Calendar.MONTH), hoje.get(Calendar.YEAR));

        try {
            fileTxt = new FileHandler(logFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.setLevel(Level.WARNING);
        LOGGER.addHandler(fileTxt);
    }

    public Boolean enviar(String codigoBarras, Long idViagemValorClasse, Boolean gratuidade, Double valor, Date dataEmissao) {
        try {
            Calendar dataLong = Calendar.getInstance();
            dataLong.setTime(dataEmissao);

            addParametro("codigoBarras", URLEncoder.encode(codigoBarras, "UTF-8"));
            addParametro("vvc_id", URLEncoder.encode(idViagemValorClasse.toString(), "UTF-8"));
            addParametro("valor", URLEncoder.encode(gratuidade.toString(), "UTF-8"));
            addParametro("data_emissao", URLEncoder.encode(new Long(dataLong.getTimeInMillis()).toString(), "UTF-8"));

            RespostaDTO respostaDTO = get("/ws/checkin", JAXBContext.newInstance(RespostaDTO.class));

            if(!respostaDTO.isSucesso()) {
                LOGGER.warning(String.format("Erro do servidor para o código %s: %s.", codigoBarras, respostaDTO.getMensagem()));
            } else {
                return true;
            }
        } catch (UnsupportedEncodingException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionDetails = sw.toString();
            LOGGER.warning(exceptionDetails);
        } catch (JAXBException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionDetails = sw.toString();
            LOGGER.warning(exceptionDetails);
        } catch (IOException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionDetails = sw.toString();
            LOGGER.warning(exceptionDetails);
        }

        return false;
    }
}