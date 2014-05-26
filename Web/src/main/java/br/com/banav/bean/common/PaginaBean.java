package br.com.banav.bean.common;

import br.com.banav.model.Usuario;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * Created by GilsonRocha on 22/01/14.
 */
public class PaginaBean implements Serializable {

    public static final String USUARIO_SESSAO = "usuario";

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

    /**
     * Adiciona um novo atributo na sessão.
     *
     * @param chave
     * @param valor
     */
    public void addAtributo(String chave, Object valor) {
        getSession().setAttribute(chave, valor);
    }

    /**
     * Retorna o valor de um atributo da sessão dado uma chave.
     *
     * @param chave
     * @return valor do tipo Object
     */
    public Object getAtributo(String chave) {
        if(getSession() != null)
            return getSession().getAttribute(chave);

        return null;
    }

    /**
     * Remove o valor de um atributo da sessão.
     *
     * @param chave
     * @return valor do tipo Object
     */
    public Object removerAtributo(String chave) {
        Object obj = getSession().getAttribute(chave);
        getSession().removeAttribute(chave);
        return obj;
    }

    /**
     * Retorna a sessão aberta.
     *
     * @return HttpSession
     */
    private HttpSession getSession() {
        Object session = FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(session == null)
            return null;

        return (HttpSession) session;
    }

    protected Usuario getUsuarioLogado() {
        Object obj = getAtributo(USUARIO_SESSAO);
        if(obj == null)
            return null;

        return (Usuario) obj;
    }

    /**
     * Retorna uma referência do HttpServletRequest.
     *
     * @return HttpServletRequest
     */
    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
}
