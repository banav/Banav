package br.com.banav.bean;

import br.com.banav.bean.common.PaginaBean;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
@URLMappings(mappings = {
    @URLMapping(id = "home", pattern = "/home/", viewId = "/pages/home.jsf", parentId = "paginaRestrita")
})
public class HomeBean extends PaginaBean {

	private static final long serialVersionUID = -5160213396314807902L;

	@URLAction(mappingId = "home", onPostback = false)
	public void abrir() {

	}
}