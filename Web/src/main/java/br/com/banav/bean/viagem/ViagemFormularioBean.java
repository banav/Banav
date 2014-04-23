package br.com.banav.bean.viagem;

import br.com.banav.bean.common.PaginaBean;
import br.com.banav.model.*;
import br.com.banav.service.NavioSrv;
import br.com.banav.service.PortoSrv;
import br.com.banav.service.ViagemSrv;
import br.com.banav.service.ViagemValorClasseSrv;
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
 * Created by GilsonRocha on 29/01/14.
 */
@ManagedBean
@ViewScoped
@URLMappings(mappings = {
    @URLMapping(id = "viagemFormulario", pattern = "/viagem/nova", viewId = "/pages/viagem/viagem_formulario.jsf", parentId = "paginaRestrita"),
    @URLMapping(id = "viagemEditar", pattern = "/viagem/#{id : viagemFormularioBean.id}", viewId = "/pages/viagem/viagem_formulario.jsf", parentId = "paginaRestrita")
})
public class ViagemFormularioBean extends PaginaBean {

    @EJB private ViagemSrv viagemSrv;

    @EJB private NavioSrv navioSrv;

    @EJB private PortoSrv portoSrv;

    @EJB private ViagemValorClasseSrv viagemValorClasseSrv;

    private Long id;

    private List<Navio> navios;

    private List<Porto> portos;

    private List<ViagemValorClasse> viagemValores;

    private Viagem viagem;

    private Integer frequencia;

    private Integer repeticoes;

    @URLActions(actions = {
        @URLAction(mappingId = "viagemFormulario", onPostback = false),
        @URLAction(mappingId = "viagemEditar", onPostback = false)
    })
    public void abrir() {
        viagemValores = new ArrayList<ViagemValorClasse>();

        navios = navioSrv.listar();
        portos = portoSrv.listar();

        if(id == null) {
            repeticoes = 1;
            viagem = new Viagem();
        } else {
            viagem = viagemSrv.getUm(id);
            viagemValores = viagemValorClasseSrv.listarPor(viagem);
        }
    }

    public void salvar() {
        if(id == null) {
            if(Frequencia.NUNCA.ordinal() == frequencia.intValue()) {
                viagemSrv.salvar(viagem, Frequencia.NUNCA, repeticoes, viagemValores);
            } else if(Frequencia.DIARIAMENTE.ordinal() == frequencia.intValue()) {
                viagemSrv.salvar(viagem, Frequencia.DIARIAMENTE, repeticoes, viagemValores);
            } else if(Frequencia.SEMANALMENTE.ordinal() == frequencia.intValue()) {
                viagemSrv.salvar(viagem, Frequencia.SEMANALMENTE, repeticoes, viagemValores);
            } else if(Frequencia.MENSALMENTE.ordinal() == frequencia.intValue()) {
                viagemSrv.salvar(viagem, Frequencia.MENSALMENTE, repeticoes, viagemValores);
            }

            viagem = new Viagem();
            repeticoes = 1;
        } else {
            viagemSrv.atualizar(viagem);
        }

        addInfo("Salvo com sucesso.");
    }

    public void handleNavio() {
        if(viagem != null && viagem.getNavio() != null) {
            viagemValores = viagemValorClasseSrv.listarPor(viagem.getNavio());
        } else {
            viagemValores = new ArrayList<ViagemValorClasse>();
        }
    }

    public List<Navio> getNavios() {
        return navios;
    }

    public void setNavios(List<Navio> navios) {
        this.navios = navios;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public List<Porto> getPortos() {
        return portos;
    }

    public void setPortos(List<Porto> portos) {
        this.portos = portos;
    }

    public Integer getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Integer frequencia) {
        this.frequencia = frequencia;
    }

    public Integer getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(Integer repeticoes) {
        this.repeticoes = repeticoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ViagemValorClasse> getViagemValores() {
        return viagemValores;
    }

    public void setViagemValores(List<ViagemValorClasse> viagemValores) {
        this.viagemValores = viagemValores;
    }
}