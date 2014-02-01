package br.com.banav.bean.classe;

import br.com.banav.bean.common.PaginaBean;
import br.com.banav.model.Cidade;
import br.com.banav.model.Classe;
import br.com.banav.model.Estado;
import br.com.banav.service.ClasseSrv;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLActions;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Created by gustavocosta on 01/02/14.
 */

@ManagedBean
@ViewScoped
@URLMappings(mappings = {
        @URLMapping(id = "classeNovo", pattern = "/classe/novo", viewId = "/pages/classe/classe_formulario.jsf"),
        @URLMapping(id = "classeEditar", pattern = "/classe/#{id : classeFormularioBean.id}", viewId = "/pages/classe/classe_formulario.jsf")
})
public class ClasseFormularioBean extends PaginaBean{

    private Long id;

    private Classe classe;

    @EJB
    private ClasseSrv classeSrv;

    @URLActions(actions = {
            @URLAction(mappingId = "classeNovo", onPostback = false),
            @URLAction(mappingId = "classeEditar", onPostback = false)
    })
    public void abrir() {

        if(id != null){
            classe = classeSrv.getUm(id);
        }
        else{
            classe = new Classe();
        }


    }

    public void salvar() {

        if(id != null)
            classeSrv.atualizar(classe);
        else{
            classeSrv.salvar(classe);
            classe = new Classe();
        }
        addInfo("Salvo com sucesso.");
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
