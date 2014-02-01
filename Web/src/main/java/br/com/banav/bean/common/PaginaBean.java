package br.com.banav.bean.common;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by GilsonRocha on 22/01/14.
 */
public class PaginaBean {

    /**
     * Adiciona uma nova mensagem do tipo info.
     *
     * @param mensagem
     */
    public void addInfo(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO ,"Informação", mensagem));
    }

    /**
     * Adiciona uma nova mensagem do tipo warn.
     *
     * @param mensagem
     */
    public void addWarn(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Atenção", mensagem));
    }
}
