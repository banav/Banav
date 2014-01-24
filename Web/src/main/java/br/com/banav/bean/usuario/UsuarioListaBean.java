package br.com.banav.bean.usuario;

import br.com.banav.bean.common.PaginaBean;
import br.com.banav.model.Usuario;
import br.com.banav.service.UsuarioSrv;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GilsonRocha on 22/01/14.
 */
@ManagedBean
@ViewScoped
@URLMappings(mappings = {
    @URLMapping(id = "usuarioLista", pattern = "/usuario/lista", viewId = "/pages/usuario/usuario_lista.jsf")
})
public class UsuarioListaBean extends PaginaBean {

    @EJB private UsuarioSrv usuarioSrv;

    private List<Usuario> usuarios;

    @URLAction(mappingId = "usuarioLista", onPostback = false)
    public void listar() {
        usuarios = usuarioSrv.listar();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
