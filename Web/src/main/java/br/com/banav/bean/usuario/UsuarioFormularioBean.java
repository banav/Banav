package br.com.banav.bean.usuario;

import br.com.banav.bean.common.PaginaBean;
import br.com.banav.model.Usuario;
import br.com.banav.service.UsuarioSrv;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLActions;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Random;

/**
 * Created by GilsonRocha on 22/01/14.
 */
@ManagedBean
@ViewScoped
@URLMappings(mappings = {
    @URLMapping(id = "usuarioNovo", pattern = "/usuario/novo", viewId = "/pages/usuario/usuario_formulario.jsf"),
    @URLMapping(id = "usuarioEditar", pattern = "/usuario/#{id : usuarioFormularioBean.id}", viewId = "/pages/usuario/usuario_formulario.jsf")
})
public class UsuarioFormularioBean extends PaginaBean {

    @EJB private UsuarioSrv usuarioSrv;

    private Integer id;

    private Usuario usuario;

    @URLActions(actions = {
        @URLAction(mappingId = "usuarioNovo", onPostback = false),
        @URLAction(mappingId = "usuarioEditar", onPostback = false)
    })
    public void abrir() {
        if(id != null) {
            usuario = usuarioSrv.getUm(id);
        } else {
            usuario = new Usuario();
        }
    }

    public void salvar() {
        if(id != null) {
            usuarioSrv.atualizar(usuario);
        } else {
            usuarioSrv.salvar(usuario);
            usuario = new Usuario();
        }

        addInfo("Salvo com sucesso.");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
