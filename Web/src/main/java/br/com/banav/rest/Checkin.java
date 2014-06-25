package br.com.banav.rest;

import br.com.banav.model.Navio;
import br.com.banav.service.CheckinSrv;
import br.com.banav.service.NavioSrv;
import javafx.application.Application;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Created by Microsoft on 23/06/2014.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/checkin")
public class Checkin {
    // The Java method will process HTTP GET requests


    @Inject
    private CheckinSrv checkinSrv;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public RespostaDTO getClichedMessage(@QueryParam("codigoBarras") String codigoBarras) {

        RespostaDTO dto = new RespostaDTO();
        dto.setMensagem(codigoBarras);
        dto.setSucesso(true);

        if(codigoBarras != null && !codigoBarras.isEmpty()){
            try {
                checkinSrv.efeturarCheckin(codigoBarras);
                dto.setSucesso(true);
                dto.setMensagem("Checkin realizado com sucesso!");
            }
            catch (Exception e){
                dto.setMensagem(e.getMessage());
                dto.setSucesso(false);
            }
        }

        return dto;
    }


}
