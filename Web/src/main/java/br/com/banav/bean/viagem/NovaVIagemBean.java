package br.com.banav.bean.viagem;

import br.com.banav.bean.common.PaginaBean;
import br.com.banav.model.Frequencia;
import br.com.banav.model.Navio;
import br.com.banav.model.Porto;
import br.com.banav.model.Viagem;
import br.com.banav.service.NavioSrv;
import br.com.banav.service.PortoSrv;
import br.com.banav.service.ViagemSrv;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import org.primefaces.event.TabChangeEvent;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by GilsonRocha on 29/01/14.
 */
@ManagedBean
@ViewScoped
@URLMappings(mappings = {
    @URLMapping(id = "novaViagem", pattern = "/viagem/nova", viewId = "/pages/viagem/nova_viagem.jsf")
})
public class NovaViagemBean extends PaginaBean {

    @EJB
    private ViagemSrv viagemSrv;

    @EJB
    private NavioSrv navioSrv;

    @EJB
    private PortoSrv portoSrv;

    private List<Navio> navios;

    private List<Porto> portos;

    private Viagem viagem;

    private Integer frequencia;

    private Integer repeticoes;

    @URLAction(mappingId = "novaViagem", onPostback = false)
    public void abrir() {
        repeticoes = 1;
        navios = navioSrv.listar();
        portos = portoSrv.listar();
        viagem = new Viagem();
    }

    public void salvar() {
        if(Frequencia.NUNCA.ordinal() == frequencia.intValue()) {
            viagemSrv.salvar(viagem, Frequencia.NUNCA, repeticoes);
        } else if(Frequencia.DIARIAMENTE.ordinal() == frequencia.intValue()) {
            viagemSrv.salvar(viagem, Frequencia.DIARIAMENTE, repeticoes);
        } else if(Frequencia.SEMANALMENTE.ordinal() == frequencia.intValue()) {
            viagemSrv.salvar(viagem, Frequencia.SEMANALMENTE, repeticoes);
        } else if(Frequencia.MENSALMENTE.ordinal() == frequencia.intValue()) {
            viagemSrv.salvar(viagem, Frequencia.MENSALMENTE, repeticoes);
        }

        addInfo("Salvo com sucesso.");

        viagem = new Viagem();
        repeticoes = 1;
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
}