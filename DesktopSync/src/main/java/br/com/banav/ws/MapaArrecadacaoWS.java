package br.com.banav.ws;

import br.com.banav.ws.dto.MapaArrecadacaoDTO;
import br.com.banav.ws.dto.MapaViagemDTO;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.eclipse.persistence.jaxb.MarshallerProperties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by gustavocosta on 12/09/15.
 */
public class MapaArrecadacaoWS extends RestClient{


    private final static Logger LOGGER = Logger.getLogger(MapaArrecadacaoWS.class.getName());


    public boolean salvar(MapaArrecadacaoDTO arrecadacaoDTO){

        try {
            String urlPost = getBaseURL() + "/mapa/salvar";

            HttpClient  httpClient = HttpClientBuilder.create().build();

            HttpPost httpPost = new HttpPost(urlPost);

            httpPost.setEntity(new StringEntity(getJson(arrecadacaoDTO)));

            HttpResponse httpResponse = httpClient.execute(httpPost);

            RespostaDTO dto = getResult(httpResponse.getEntity().getContent(), JAXBContext.newInstance(RespostaDTO.class));

            if(dto.isSucesso())
                return true;
            else
                LOGGER.warning(String.format("Erro do servidor: %s.", dto.getMensagem()));


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


    private String getJson(MapaArrecadacaoDTO dto) throws  JAXBException{

        JAXBContext jc = JAXBContext.newInstance(MapaArrecadacaoDTO.class, MapaViagemDTO.class);

        // Create the Marshaller Object using the JaxB Context

        Marshaller marshaller = jc.createMarshaller();


        // Set the Marshaller media type to JSON or XML
        marshaller.setProperty(MarshallerProperties.MEDIA_TYPE,
                "application/json");

        // Set it to true if you need to include the JSON root element in the JSON output
        marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
        // Set it to true if you need the JSON output to formatted
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        // Marshal the employee object to JSON and print the output to console

        StringWriter w = new StringWriter();
        marshaller.marshal(dto, w);

        return w.toString();


    }


}
