package br.com.banav.bean.cidade;

import br.com.banav.bean.common.PaginaBean;
import br.com.banav.model.Cidade;
import br.com.banav.model.Estado;
import br.com.banav.service.CidadeSrv;
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
        @URLMapping(id = "cidadeNovo", pattern = "/cidade/novo", viewId = "/pages/cidade/cidade_formulario.jsf"),
        @URLMapping(id = "cidadeEditar", pattern = "/cidade/#{id : cidadeFormularioBean.id}", viewId = "/pages/cidade/cidade_formulario.jsf")
})
public class CidadeFormularioBean extends PaginaBean {


    @EJB
    private CidadeSrv cidadeSrv;

    private Cidade cidade;

    private List<Estado> estados;

    private Long id;



    @URLActions(actions = {
            @URLAction(mappingId = "cidadeNovo", onPostback = false),
            @URLAction(mappingId = "cidadeEditar", onPostback = false)
    })
    public void abrir() {

        estados = cidadeSrv.listarEstado();

        if(id != null){
            cidade = cidadeSrv.getUm(id);
        }
        else{
            cidade = new Cidade();
            cidade.setEstado(new Estado());
        }


    }

    public void salvar() {

        if(id != null)
            cidadeSrv.atualizar(cidade);
        else{
            cidadeSrv.salvar(cidade);
            cidade = new Cidade();
        }
        addInfo("Salvo com sucesso.");
    }


    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
