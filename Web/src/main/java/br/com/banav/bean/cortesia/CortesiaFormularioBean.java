package br.com.banav.bean.cortesia;

import br.com.banav.bean.common.PaginaBean;
import br.com.banav.model.Cortesia;
import br.com.banav.model.Estado;
import br.com.banav.service.CortesiaSrv;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLActions;
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
        @URLMapping(id = "cortesiaNovo", pattern = "/cortesia/novo", viewId = "/pages/cortesia/cortesia_formulario.jsf"),
        @URLMapping(id = "cortesiaEditar", pattern = "/cortesia/editar/#{id : cortesiaFormularioBean.id}", viewId = "/pages/cortesia/cidade_formulario.jsf")
})
public class CortesiaFormularioBean extends PaginaBean {


    @EJB
    private CortesiaSrv cortesiaSrv;

    private Cortesia cortesia;

    private List<Estado> estados;

    private Long id;

    @URLActions(actions = {
        @URLAction(mappingId = "cortesiaNovo", onPostback = false),
        @URLAction(mappingId = "cortesiaEditar", onPostback = false)
    })
    public void abrir() {
        if(id != null){
            cortesia = cortesiaSrv.getUm(id);
        }
        else{
            cortesia = new Cortesia();
        }
    }

    public void salvar() {

        if(id != null)
            cortesiaSrv.atualizar(cortesia);
        else{
            cortesiaSrv.salvar(cortesia);
            cortesia = new Cortesia();
        }
        addInfo("Salvo com sucesso.");
    }
}
