package br.com.banav.ws;

import br.com.banav.ws.dto.ClasseDTO;
import br.com.banav.ws.dto.ClasseListDTO;
import br.com.banav.ws.dto.NavioDTO;
import br.com.banav.ws.dto.NavioListDTO;

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
public class ClasseWS extends RestClient {

    private static FileHandler fileTxt;

    private final static Logger LOGGER = Logger.getLogger(ClasseWS.class.getName());

    public ClasseWS() {
        Calendar hoje = Calendar.getInstance();
        String logFile = String.format("%s-%s-%s-classe-ws.txt", hoje.get(Calendar.DAY_OF_MONTH), hoje.get(Calendar.MONTH), hoje.get(Calendar.YEAR));

        try {
            fileTxt = new FileHandler(logFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.setLevel(Level.WARNING);
        LOGGER.addHandler(fileTxt);
    }

    public List<ClasseDTO> listar(Date date) {
        try {
            ClasseListDTO classeListDTO = new ClasseListDTO();
            if(date == null)
                classeListDTO = get("/ws/classe/list", JAXBContext.newInstance(ClasseListDTO.class));
            else{
            String url = "/ws/classe/list/" + date.getTime();
            classeListDTO = get(url, JAXBContext.newInstance(ClasseListDTO.class));
            }
            return classeListDTO.getCollection();
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