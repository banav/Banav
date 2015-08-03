package br.com.banav.venda.bean.common;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLAction.PhaseId;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
@URLMappings(mappings={
    @URLMapping(id = "paginaRestrita", pattern = "", viewId = "/blank.jsf"),
    @URLMapping(id = "paginaLogin", pattern = "", viewId = "/blank.jsf")
})
public class PaginaRestritaBean extends PaginaBean {

	private static final long serialVersionUID = 2934958898861604048L;

	@URLAction(mappingId = "paginaRestrita", inheritable = true, onPostback = false, phaseId = PhaseId.RESTORE_VIEW)
    public String validarUsuario() {
        if (getUsuarioLogado() != null) {
            return null;
        } else {
            return "pretty:login";
        }
    }
	
	@URLAction(mappingId = "paginaLogin", inheritable = true, onPostback = false, phaseId = PhaseId.RESTORE_VIEW)
    public String validarSessaoUsuario() {
        if (getUsuarioLogado() != null) {
            return "pretty:home";
        } else {
            return null;
        }
    }
}

