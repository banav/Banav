package br.com.banav.venda.bean;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Created by gilson on 13/06/15.
 */
@ManagedBean
@ViewScoped
@URLMappings(mappings = {
    @URLMapping(id = "homeVenda", pattern = "/principal/", viewId = "/home.jsf"),
    @URLMapping(id = "homeVenda2", pattern = "/principal", viewId = "/home.jsf")
})
public class HomeBean {

    @URLAction(mappingId = "homeVenda", onPostback = false)
    public void init() {

    }
}
