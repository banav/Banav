package br.com.banav.bean.navios;

import br.com.banav.bean.common.PaginaBean;
import br.com.banav.model.Classe;
import br.com.banav.model.Navio;
import br.com.banav.model.NavioClasse;
import br.com.banav.service.ClasseSrv;
import br.com.banav.service.NavioSrv;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLActions;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavocosta on 02/02/14.
 */

@Named
@ConversationScoped
@URLMappings(mappings = {
    @URLMapping(id = "navioNovo", pattern = "/navios/novo", viewId = "/pages/navios/navio_formulario.jsf", parentId = "paginaRestrita"),
    @URLMapping(id = "naviosEditar", pattern = "/navio/editar#{id : naviosFormularioBean.id}", viewId = "/pages/navios/navio_formulario.jsf", parentId = "paginaRestrita"),
    @URLMapping(id = "salvarNavioClasse", pattern = "/navio/novo", viewId = "/pages/navios/navio_formulario.jsf", parentId = "paginaRestrita"),
    @URLMapping(id = "novoClasseNavio", pattern = "/navio/classe/novo", viewId = "/pages/navios/navios_classes_formulario.jsf", parentId = "paginaRestrita")
})
public class NaviosFormularioBean extends PaginaBean implements Serializable {

    private Long id;

    private Navio navio;

    private NavioClasse navioClasse;

    private List<Classe> classes;

    @Inject
    private NavioSrv navioSrv;

    @Inject
    private ClasseSrv classeSrv;

    @Inject
    private Conversation conversation;


    @URLActions(actions = {
            @URLAction(mappingId = "navioNovo", onPostback = false),
            @URLAction(mappingId = "naviosEditar", onPostback = false)
    })
    public void abrir() {

        beginConversation();

        if(id != null){
            navio = navioSrv.getUm(id);
        }
        else{
            navio = new Navio();
        }

    }

    public String salvarNavio(){

        if(navio.getNavioID() == null){
            navioSrv.salvar(navio);
        }
        else
            navioSrv.atualizar(navio);

        endConversation();

        return "/pages/navios/navio_lista.xhtml";
    }

    public String salvarNavioClasse(){
        List<NavioClasse> classes = navio.getClasses();

        if(classes == null){
            classes = new ArrayList<NavioClasse>();
            navio.setClasses(classes);
        }
        navioClasse.setNavio(this.navio);
        classes.add(navioClasse);

        navioClasse = new NavioClasse();
        navioClasse.setNavio(navio);

        return "/pages/navios/navio_formulario.xhtml";
    }

    private void beginConversation(){
        if(conversation.isTransient()){
            conversation.begin();
        }
    }

    public void endConversation(){
        if(!conversation.isTransient()){
            conversation.end();
        }
    }

    public String novaClasse(){

        if(classes == null)
            classes = classeSrv.listar();

        navioClasse = new NavioClasse();
        navioClasse.setClasse(new Classe());
        navioClasse.setNavio(this.navio);

        return "/pages/navios/navios_classes_formulario.xhtml";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Navio getNavio() {
        return navio;
    }

    public void setNavio(Navio navio) {
        this.navio = navio;
    }

    public NavioClasse getNavioClasse() {
        return navioClasse;
    }

    public void setNavioClasse(NavioClasse navioClasse) {
        this.navioClasse = navioClasse;
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }
}