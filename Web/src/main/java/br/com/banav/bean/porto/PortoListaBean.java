package br.com.banav.bean.porto;

import br.com.banav.bean.common.PaginaBean;
import br.com.banav.model.Porto;
import br.com.banav.service.PortoSrv;
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
    @URLMapping(id = "portoLista", pattern = "/porto/lista", viewId = "/pages/porto/porto_lista.jsf", parentId = "paginaRestrita")
})
public class PortoListaBean extends PaginaBean {

    @EJB
    private PortoSrv portoSrv;

    private List<Porto> portos;

    @URLAction(onPostback = false, mappingId = "portoLista")
    public void listar(){
        portos = portoSrv.listar();
    }

    public List<Porto> getPortos() {
        return portos;
    }

    public void setPortos(List<Porto> portos) {
        this.portos = portos;
    }
}
