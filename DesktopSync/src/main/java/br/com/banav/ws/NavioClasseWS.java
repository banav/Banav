package br.com.banav.ws;

import br.com.banav.ws.dto.NavioClasseDTO;
import br.com.banav.ws.dto.NavioClasseListDTO;

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
public class NavioClasseWS extends RestClient {

    private static FileHandler fileTxt;

    private final static Logger LOGGER = Logger.getLogger(NavioClasseWS.class.getName());

    public NavioClasseWS() {
        Calendar hoje = Calendar.getInstance();
        String logFile = String.format("%s-%s-%s-navioclasse-ws.txt", hoje.get(Calendar.DAY_OF_MONTH), hoje.get(Calendar.MONTH), hoje.get(Calendar.YEAR));

        try {
            fileTxt = new FileHandler(logFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.setLevel(Level.WARNING);
        LOGGER.addHandler(fileTxt);
    }

    public List<NavioClasseDTO> listar(Date date) {
        try {
            NavioClasseListDTO listDTO = new NavioClasseListDTO();
            if(date == null)
                listDTO = get("/ws/navioclasse/list", JAXBContext.newInstance(NavioClasseListDTO.class));
            else{
                String url = "/ws/navioclasse/list/" + date.getTime();
                listDTO = get(url, JAXBContext.newInstance(NavioClasseListDTO.class));
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