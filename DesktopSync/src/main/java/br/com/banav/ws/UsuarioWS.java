package br.com.banav.ws;

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
public class UsuarioWS extends RestClient {

    private static FileHandler fileTxt;

    private final static Logger LOGGER = Logger.getLogger(UsuarioWS.class.getName());

    public UsuarioWS() {
        Calendar hoje = Calendar.getInstance();
        String logFile = String.format("%s-%s-%s-usuario-ws.txt", hoje.get(Calendar.DAY_OF_MONTH), hoje.get(Calendar.MONTH), hoje.get(Calendar.YEAR));

        try {
            fileTxt = new FileHandler(logFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.setLevel(Level.WARNING);
        LOGGER.addHandler(fileTxt);
    }

    public List<UsuarioDTO> listar(Date date) {
        try {
            UsuarioListDTO usuarioListDTO = null;
            if(date == null)

                usuarioListDTO = get("/ws/usuario/list", JAXBContext.newInstance(UsuarioListDTO.class));
            else{
                String url = "/ws/usuario/list/" + date.getTime();
                usuarioListDTO = get(url, JAXBContext.newInstance(UsuarioListDTO.class));
            }
            return usuarioListDTO.getCollection();
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