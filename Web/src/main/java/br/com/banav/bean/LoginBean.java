package br.com.banav.bean;

import br.com.banav.bean.common.PaginaBean;
import br.com.banav.model.Perfil;
import br.com.banav.model.Usuario;
import br.com.banav.service.UsuarioSrv;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

@ManagedBean
@SessionScoped
@URLMapping(id = "login", pattern = "/login/", viewId = "/pages/login.jsf")
public class LoginBean extends PaginaBean {
	
	private static final long serialVersionUID = 7771266417791608473L;
	
	@Inject
	private UsuarioSrv usuarioSrv;
	
	private Usuario usuario;
	
	@URLAction(mappingId = "login")
	public void abrir() {
		usuario = new Usuario();
	}
	
	public String logar() {
		usuario = usuarioSrv.logar(usuario);
		addAtributo("usuario", usuario);
		
		return "pretty:home";
	}
	
	public String sair() {
		removerAtributo(USUARIO_SESSAO);
		return "pretty:login";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Boolean isAdministrador() {
		return usuario.getPerfil().equals(Perfil.ADMINISTRADOR);
	}
	
	public Boolean isVendedor() {
        return usuario.getPerfil().equals(Perfil.VENDEDOR);
	}

    public Boolean isCheckIn() {
        return usuario.getPerfil().equals(Perfil.CHECKIN);
    }
}
