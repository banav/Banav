package br.com.banav.ws;

import br.com.banav.ws.dto.CortesiaDTO;
import br.com.banav.ws.dto.CortesiaListDTO;
import br.com.banav.ws.dto.ViagemDTO;
import br.com.banav.ws.dto.ViagemListDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by gilson on 6/24/14.
 */
public class CortesiaWS extends RestClient {

    private static FileHandler fileTxt;

    private final static Logger LOGGER = Logger.getLogger(CortesiaWS.class.getName());

    public CortesiaWS() {
        Calendar hoje = Calendar.getInstance();
        String logFile = String.format("%s-%s-%s-cortesia-ws.txt", hoje.get(Calendar.DAY_OF_MONTH), hoje.get(Calendar.MONTH), hoje.get(Calendar.YEAR));

        try {
            fileTxt = new FileHandler(logFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.setLevel(Level.WARNING);
        LOGGER.addHandler(fileTxt);
    }

    public List<CortesiaDTO> listar(Date date) {
        try {
            CortesiaListDTO listDTO = new CortesiaListDTO();
            if(date == null)
                listDTO = get("/ws/cortesia/list", JAXBContext.newInstance(CortesiaListDTO.class));
            else{
                String url = "/ws/cortesia/list/" + date.getTime();
                listDTO = get(url, JAXBContext.newInstance(CortesiaListDTO.class));
            }
            return listDTO.getCollection();
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

        return null;
    }
}