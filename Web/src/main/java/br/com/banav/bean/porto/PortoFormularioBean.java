package br.com.banav.bean.porto;

import br.com.banav.bean.common.PaginaBean;
import br.com.banav.model.Cidade;
import br.com.banav.model.Endereco;
import br.com.banav.model.Estado;
import br.com.banav.model.Porto;
import br.com.banav.service.CidadeSrv;
import br.com.banav.service.PortoSrv;
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
    @URLMapping(id = "portoNovo", pattern = "/porto/novo", viewId = "/pages/porto/porto_formulario.jsf", parentId = "paginaRestrita"),
    @URLMapping(id = "portoEditar", pattern = "/porto/editar/#{id : portoFormularioBean.id}", viewId = "/pages/porto/porto_formulario.jsf", parentId = "paginaRestrita")
})
public class PortoFormularioBean extends PaginaBean {

    @EJB
    private PortoSrv portoSrv;

    @EJB
    private CidadeSrv cidadeSrv;

    private List<Cidade> cidades;

    private Porto porto;

    private Long id;

    @URLActions(actions = {
        @URLAction(mappingId = "portoNovo", onPostback = false),
        @URLAction(mappingId = "portoEditar", onPostback = false)
    })
    public void abrir() {

        cidades = cidadeSrv.listar();

        if(id != null){
            porto = portoSrv.getUm(id);
        }
        else{

            Cidade cidade = new Cidade();

            Endereco endereco = new Endereco();
            endereco.setCidade(cidade);

            porto = new Porto();
            porto.setEndereco(endereco);
        }


    }

    public void salvar() {
        System.out.println("chega aqui");
        if(id != null)
            portoSrv.atualizar(porto);
        else{
            portoSrv.salvar(porto);
            porto = new Porto();
        }
        addInfo("Salvo com sucesso.");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Porto getPorto() {
        return porto;
    }

    public void setPorto(Porto porto) {
        this.porto = porto;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }
}
