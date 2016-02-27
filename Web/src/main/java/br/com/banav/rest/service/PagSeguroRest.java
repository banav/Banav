package br.com.banav.rest.service;

import br.com.banav.model.PagSeguro;
import br.com.banav.rest.dto.vendas.ValorClasseDTO;
import br.com.banav.service.CidadeSrv;
import br.com.banav.service.PagSeguroSrv;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by gilson on 27/02/16.
 */
@Path("/pagseguro")
public class PagSeguroRest {

    @Inject private PagSeguroSrv pagSeguroSrv;

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(List<ValorClasseDTO> valorClassesDTO) {
        PagSeguro pagSeguro = pagSeguroSrv.salvar(valorClassesDTO);
        return Response.ok(pagSeguro).build();
    }
}
