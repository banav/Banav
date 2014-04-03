package br.com.banav.bean.passagem;

import br.com.banav.bean.common.PaginaBean;
import br.com.banav.model.Passagem;
import br.com.banav.model.Porto;
import br.com.banav.service.PassagemSrv;
import br.com.banav.service.PortoSrv;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by gustavocosta on 01/02/14.
 */

@ManagedBean
@ViewScoped
@URLMappings(mappings = {
    @URLMapping(id = "passagemLista", pattern = "/passagem/lista", viewId = "/pages/passagem/passagem_lista.jsf")
})
public class PassagemListaBean extends PaginaBean {

    @EJB
    private PassagemSrv passagemSrv;

    private List<Passagem> passagens;

    @URLAction(onPostback = false, mappingId = "passagemLista")
    public void listar(){
        passagens = passagemSrv.listar();
    }

    public List<Passagem> getPassagens() {
        return passagens;
    }

    public void setPassagens(List<Passagem> passagens) {
        this.passagens = passagens;
    }
}
