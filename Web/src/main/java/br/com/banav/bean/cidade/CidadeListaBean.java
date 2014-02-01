package br.com.banav.bean.cidade;

import br.com.banav.bean.common.PaginaBean;
import br.com.banav.model.Cidade;
import br.com.banav.service.CidadeSrv;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by gustavocosta on 01/02/14.
 */

@ManagedBean
@ViewScoped
@URLMappings(mappings = {
        @URLMapping(id = "cidadeLista", pattern = "/cidade/lista", viewId = "/pages/cidade/cidade_lista.jsf")
})
public class CidadeListaBean extends PaginaBean {

    @EJB
    private CidadeSrv cidadeSrv;

    private List<Cidade> cidades;

    @URLAction(mappingId = "cidadeLista", onPostback = false)
    public void listar(){
        cidades = cidadeSrv.listar();
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }
}
