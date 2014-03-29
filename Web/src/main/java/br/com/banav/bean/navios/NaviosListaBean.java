package br.com.banav.bean.navios;

import br.com.banav.bean.common.PaginaBean;
import br.com.banav.model.Navio;
import br.com.banav.service.NavioSrv;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by gustavocosta on 02/02/14.
 */

@ManagedBean
@ViewScoped
@URLMappings(mappings = {
        @URLMapping(id = "nList", pattern = "/navio/lista", viewId = "/pages/navios/navio_lista.jsf")
})
public class NaviosListaBean extends PaginaBean{

    private List<Navio> navios;

    @EJB
    private NavioSrv navioSrv;

    @URLAction(mappingId = "nList", onPostback = false)
    public void listar(){
        navios = navioSrv.listar();
    }

    public List<Navio> getNavios() {
        return navios;
    }

    public void setNavios(List<Navio> navios) {
        this.navios = navios;
    }
}
