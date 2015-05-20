package br.com.banav.bean.usuario;

import br.com.banav.bean.common.PaginaBean;
import br.com.banav.model.Cidade;
import br.com.banav.model.Endereco;
import br.com.banav.model.Perfil;
import br.com.banav.model.Usuario;
import br.com.banav.service.CidadeSrv;
import br.com.banav.service.UsuarioSrv;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLActions;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by GilsonRocha on 22/01/14.
 */
@ManagedBean
@ViewScoped
@URLMappings(mappings = {
    @URLMapping(id = "usuarioNovo", pattern = "/usuario/novo", viewId = "/pages/usuario/usuario_formulario.jsf", parentId = "paginaRestrita"),
    @URLMapping(id = "usuarioEditar", pattern = "/usuario/editar/#{id : usuarioFormularioBean.id}", viewId = "/pages/usuario/usuario_formulario.jsf", parentId = "paginaRestrita")
})
public class UsuarioFormularioBean extends PaginaBean {

    @EJB private UsuarioSrv usuarioSrv;

    @EJB private CidadeSrv cidadeSrv;

    private Long id;

    private String perfilAux;

    private Usuario usuario;

    private List<Cidade> cidades;

    @URLActions(actions = {
        @URLAction(mappingId = "usuarioNovo", onPostback = false),
        @URLAction(mappingId = "usuarioEditar", onPostback = false)
    })
    public void abrir() {
        cidades = cidadeSrv.listar();

        if(id != null) {
            usuario = usuarioSrv.getUm(id);

            if(usuario.getPerfil().equals(Perfil.ADMINISTRADOR)) perfilAux = "A";
            if(usuario.getPerfil().equals(Perfil.VENDEDOR)) perfilAux = "V";
            if(usuario.getPerfil().equals(Perfil.CHECKIN)) perfilAux = "C";

        } else {
            Cidade cidade = new Cidade();

            Endereco endereco = new Endereco();
            endereco.setCidade(cidade);

            usuario = new Usuario();
            usuario.setEndereco(endereco);
        }
    }

    public void salvar() {
        if(perfilAux.equals("A")) {
            usuario.setPerfil(Perfil.ADMINISTRADOR);
        } else if(perfilAux.equals("V")) {
            usuario.setPerfil(Perfil.VENDEDOR);
        } else if(perfilAux.equals("C")) {
            usuario.setPerfil(Perfil.CHECKIN);
        } else if(perfilAux.equals("S")) {
            usuario.setPerfil(Perfil.SUPERADMIN);
        }

        if(id != null) {
            usuarioSrv.atualizar(usuario);
        } else {
            usuarioSrv.salvar(usuario);
            usuario = new Usuario();
        }

        addInfo("Salvo com sucesso.");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public String getPerfilAux() {
        return perfilAux;
    }

    public void setPerfilAux(String perfilAux) {
        this.perfilAux = perfilAux;
    }
}
