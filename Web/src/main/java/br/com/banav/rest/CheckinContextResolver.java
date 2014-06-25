package br.com.banav.rest;

import javax.ws.rs.Produces;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;

/**
 * Created by Microsoft on 25/06/2014.
 */
@Provider
@Produces({"application/xml", "application/json"})
public class CheckinContextResolver implements ContextResolver<JAXBContext> {

    private JAXBContext jaxbContext;

    public CheckinContextResolver(){
        try {
            jaxbContext = JAXBContext.newInstance(RespostaDTO.class);
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JAXBContext getContext(Class<?> aClass) {
        if(RespostaDTO.class == aClass){
            return jaxbContext;
        }
        return null;
    }
}
