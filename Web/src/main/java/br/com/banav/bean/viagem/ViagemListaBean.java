package br.com.banav.bean.viagem;

import br.com.banav.bean.common.PaginaBean;
import br.com.banav.model.Viagem;
import br.com.banav.service.ViagemSrv;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by GilsonRocha on 01/02/14.
 */
@ManagedBean
@ViewScoped
@URLMappings(mappings = {
    @URLMapping(id = "viagemLista", pattern = "/viagem/lista", viewId = "/pages/viagem/viagem_lista.jsf", parentId = "paginaRestrita")
})
public class ViagemListaBean extends PaginaBean {

    @EJB
    private ViagemSrv viagemSrv;

    private List<Viagem> viagens;

    @URLAction(mappingId = "viagemLista", onPostback = false)
    public void listar() {
        viagens = viagemSrv.listarViagensAtivas();
    }

    public void remover(Long id) {
        viagemSrv.remover(id);
        listar();
        addInfo("Removido com sucesso.");

        //TODO Testar se a viagem já possui passagens associadas a ela.
        //addWarn("Não foi possível remover a viagem, pois ela já possui passagens compradas.");
    }

    public List<Viagem> getViagens() {
        return viagens;
    }

    public void setViagens(List<Viagem> viagens) {
        this.viagens = viagens;
    }
}
