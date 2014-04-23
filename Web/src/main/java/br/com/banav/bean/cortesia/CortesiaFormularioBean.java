package br.com.banav.bean.cortesia;

import br.com.banav.bean.common.PaginaBean;
import br.com.banav.model.Cortesia;
import br.com.banav.model.Estado;
import br.com.banav.model.Usuario;
import br.com.banav.model.Viagem;
import br.com.banav.service.CortesiaSrv;
import br.com.banav.service.UsuarioSrv;
import br.com.banav.service.ViagemSrv;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLActions;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Date;
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

    @EJB
    private ViagemSrv viagemSrv;

    @EJB
    private UsuarioSrv usuarioSrv;

    private Cortesia cortesia;

    private Viagem viagem;

    private List<Viagem> viagens;

    private Long id;

    @URLActions(actions = {
        @URLAction(mappingId = "cortesiaNovo", onPostback = false),
        @URLAction(mappingId = "cortesiaEditar", onPostback = false)
    })
    public void abrir() {
        viagens = viagemSrv.listar();

        if(id != null){
            cortesia = cortesiaSrv.getUm(id);
        }
        else{
            cortesia = new Cortesia();
        }
    }

    //TODO trocar por usuário da session
    public void salvar() {
        Usuario usuario = usuarioSrv.getUm(1L);

        if(viagem == null) {
            addWarn("Selecione uma viagem.");
            return;
        }

        if(id != null) {
            cortesiaSrv.atualizar(cortesia);
        } else {
            cortesia.setDataCriacao(new Date());
            cortesia.setUsuario(usuario);
            cortesia.setViagem(viagem);
            cortesiaSrv.salvar(cortesia);
            cortesia = new Cortesia();
        }

        addInfo("Salvo com sucesso.");
    }

    public Cortesia getCortesia() {
        return cortesia;
    }

    public void setCortesia(Cortesia cortesia) {
        this.cortesia = cortesia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public List<Viagem> getViagens() {
        return viagens;
    }

    public void setViagens(List<Viagem> viagens) {
        this.viagens = viagens;
    }
}