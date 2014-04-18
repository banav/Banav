package br.com.banav.bean.tipoGratuidade;

import br.com.banav.bean.common.PaginaBean;
import br.com.banav.model.Cidade;
import br.com.banav.model.TipoGratuidade;
import br.com.banav.service.TipoGratuidadeSrv;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by GilsonRocha on 01/02/14.
 */

@ManagedBean
@ViewScoped
@URLMappings(mappings = {
    @URLMapping(id = "tipoGratuidadeLista", pattern = "/tipoGratuidade/lista", viewId = "/pages/tipoGratuidade/tipo_gratuidade_lista.jsf")
})
public class TipoGratuidadeListaBean extends PaginaBean {

    @EJB
    private TipoGratuidadeSrv tipoGratuidadeSrv;

    private List<TipoGratuidade> tipoGratuidades;

    @URLAction(mappingId = "tipoGratuidadeLista", onPostback = false)
    public void listar(){
        tipoGratuidades = tipoGratuidadeSrv.listar();
    }

    public List<TipoGratuidade> getTipoGratuidades() {
        return tipoGratuidades;
    }

    public void setTipoGratuidades(List<TipoGratuidade> tipoGratuidades) {
        this.tipoGratuidades = tipoGratuidades;
    }
}