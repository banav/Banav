package br.com.banav.bean.cortesia;

import br.com.banav.bean.common.PaginaBean;
import br.com.banav.model.Cidade;
import br.com.banav.model.Cortesia;
import br.com.banav.service.CidadeSrv;
import br.com.banav.service.CortesiaSrv;
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
    @URLMapping(id = "cortesiaLista", pattern = "/cortesia/lista", viewId = "/pages/cortesia/cortesia_lista.jsf")
})
public class CortesiaListaBean extends PaginaBean {

    @EJB
    private CortesiaSrv cortesiaSrv;

    private List<Cortesia> cortesias;

    @URLAction(mappingId = "cortesiaLista", onPostback = false)
    public void listar(){
        cortesias = cortesiaSrv.listar();
    }

    public List<Cortesia> getCortesias() {
        return cortesias;
    }

    public void setCortesias(List<Cortesia> cortesias) {
        this.cortesias = cortesias;
    }
}
