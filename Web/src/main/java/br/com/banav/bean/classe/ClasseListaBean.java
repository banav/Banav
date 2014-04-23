package br.com.banav.bean.classe;

import br.com.banav.bean.common.PaginaBean;
import br.com.banav.model.Classe;
import br.com.banav.service.ClasseSrv;
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
    @URLMapping(id = "classeLista", pattern = "/classe/lista", viewId = "/pages/classe/classe_lista.jsf", parentId = "paginaRestrita")
})
public class ClasseListaBean extends PaginaBean{

    @EJB
    private ClasseSrv classeSrv;

    private List<Classe> classes;

    @URLAction(onPostback = false, mappingId = "classeLista")
    public void listar(){
        classes = classeSrv.listar();
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }
}
