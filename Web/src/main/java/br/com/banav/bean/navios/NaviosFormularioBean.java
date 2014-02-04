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

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavocosta on 02/02/14.
 */

@ManagedBean
@ViewScoped
@URLMappings(mappings = {
        @URLMapping(id = "navioNovo", pattern = "/navios/novo", viewId = "/pages/navios/navio_formulario.jsf"),
        @URLMapping(id = "naviosEditar", pattern = "/navio/#{id : naviosFormularioBean.id}", viewId = "/pages/navios/navio_formulario.jsf")
})
public class NaviosFormularioBean extends PaginaBean {

    private Long id;

    private Navio navio;

    private NavioClasse navioClasse;

    private List<Classe> classes;

    @EJB
    private NavioSrv navioSrv;

    @EJB
    private ClasseSrv classeSrv;


    @URLActions(actions = {
            @URLAction(mappingId = "navioNovo", onPostback = false),
            @URLAction(mappingId = "naviosEditar", onPostback = false)
    })
    public void abrir() {

        if(id != null){
            navio = navioSrv.getUm(id);
        }
        else{
            navio = new Navio();
        }

    }

    public void novaClasse(){

        if(classes == null)
            classes = classeSrv.listar();

        navioClasse = new NavioClasse();
        navioClasse.setClasse(new Classe());
    }


    public void salvarClasse(){
        System.out.println("teste");
        List<NavioClasse> classes = navio.getClasses();
        if(classes == null)
            classes = new ArrayList<NavioClasse>();
        classes.add(navioClasse);
        System.out.println("quantidade" + classes.size());

        navioClasse = new NavioClasse();
        navioClasse.setNavio(navio);
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
