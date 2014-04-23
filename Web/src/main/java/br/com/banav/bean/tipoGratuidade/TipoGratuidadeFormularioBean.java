package br.com.banav.bean.tipoGratuidade;

import br.com.banav.bean.common.PaginaBean;
import br.com.banav.model.Cidade;
import br.com.banav.model.Estado;
import br.com.banav.model.TipoGratuidade;
import br.com.banav.service.CidadeSrv;
import br.com.banav.service.TipoGratuidadeSrv;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLActions;
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
    @URLMapping(id = "tipoGratuidadeNovo", pattern = "/tipoGratuidade/novo", viewId = "/pages/tipoGratuidade/tipo_gratuidade_formulario.jsf", parentId = "paginaRestrita"),
    @URLMapping(id = "tipoGratuidadeEditar", pattern = "/tipoGratuidade/editar/#{id : tipoGratuidadeFormularioBean.id}", viewId = "/pages/tipoGratuidade/tipo_gratuidade_formulario.jsf", parentId = "paginaRestrita")
})
public class TipoGratuidadeFormularioBean extends PaginaBean {


    @EJB
    private TipoGratuidadeSrv tipoGratuidadeSrv;

    private TipoGratuidade tipoGratuidade;

    private Long id;

    @URLActions(actions = {
        @URLAction(mappingId = "tipoGratuidadeNovo", onPostback = false),
        @URLAction(mappingId = "tipoGratuidadeEditar", onPostback = false)
    })
    public void abrir() {
        if(id != null){
            tipoGratuidade = tipoGratuidadeSrv.getUm(id);
        }
        else{
            tipoGratuidade = new TipoGratuidade();
        }
    }

    public void salvar() {

        if(id != null)
            tipoGratuidadeSrv.atualizar(tipoGratuidade);
        else{
            tipoGratuidadeSrv.salvar(tipoGratuidade);
            tipoGratuidade = new TipoGratuidade();
        }
        addInfo("Salvo com sucesso.");
    }

    public TipoGratuidade getTipoGratuidade() {
        return tipoGratuidade;
    }

    public void setTipoGratuidade(TipoGratuidade tipoGratuidade) {
        this.tipoGratuidade = tipoGratuidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
