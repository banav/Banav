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
    @URLMapping(id = "usuarioNovo", pattern = "/usuario/novo", viewId = "/pages/usuario/usuario_formulario.jsf"),
    @URLMapping(id = "usuarioEditar", pattern = "/usuario/#{id : usuarioFormularioBean.id}", viewId = "/pages/usuario/usuario_formulario.jsf")
})
public class UsuarioFormularioBean extends PaginaBean {

    @EJB private UsuarioSrv usuarioSrv;

    @EJB private CidadeSrv cidadeSrv;

    private Long id;

    private String perfilAux;

    private Usuario usuario;

    private List<Cidade> cidades;

    private Perfil administrador = Perfil.ADMINISTRADOR;

    private Perfil vendedor = Perfil.VENDEDOR;

    @URLActions(actions = {
        @URLAction(mappingId = "usuarioNovo", onPostback = false),
        @URLAction(mappingId = "usuarioEditar", onPostback = false)
    })
    public void abrir() {
        cidades = cidadeSrv.listar();

        if(id != null) {
            usuario = usuarioSrv.getUm(id);
            perfilAux = usuario.getPerfil().equals(Perfil.ADMINISTRADOR) ? "A" : "V";
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
        } else {
            usuario.setPerfil(Perfil.VENDEDOR);
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

    public Perfil getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Perfil administrador) {
        this.administrador = administrador;
    }

    public Perfil getVendedor() {
        return vendedor;
    }

    public void setVendedor(Perfil vendedor) {
        this.vendedor = vendedor;
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
