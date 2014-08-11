package br.com.banav.ws;

import br.com.banav.ws.dto.ClasseDTO;
import br.com.banav.ws.dto.ClasseListDTO;
import br.com.banav.ws.dto.PortoDTO;
import br.com.banav.ws.dto.PortoListDTO;

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
public class PortoWS extends RestClient {

    private static FileHandler fileTxt;

    private final static Logger LOGGER = Logger.getLogger(PortoWS.class.getName());

    public PortoWS() {
        Calendar hoje = Calendar.getInstance();
        String logFile = String.format("%s-%s-%s-porto-ws.txt", hoje.get(Calendar.DAY_OF_MONTH), hoje.get(Calendar.MONTH), hoje.get(Calendar.YEAR));

        try {
            fileTxt = new FileHandler(logFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.setLevel(Level.WARNING);
        LOGGER.addHandler(fileTxt);
    }

    public List<PortoDTO> listar(Date date) {
        try {
            PortoListDTO listDTO = new PortoListDTO();
            if(date == null)
                listDTO = get("/ws/porto/list", JAXBContext.newInstance(PortoListDTO.class));
            else{
                String url = "/ws/porto/list/" + date.getTime();
                listDTO = get(url, JAXBContext.newInstance(PortoListDTO.class));
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