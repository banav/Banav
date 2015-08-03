package br.com.banav.venda.bean.common;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.faces.context.FacesContext.getCurrentInstance;

public class BanavActionListener implements ActionListener {

	private static Logger logger = Logger.getLogger(BanavActionListener.class.getName());

	private final ActionListener delegate;

	public BanavActionListener(ActionListener delegate) {
		this.delegate = delegate;
	}

	public void processAction(ActionEvent event) throws AbortProcessingException {
		try {
			delegate.processAction(event);
		} catch (Exception e) {
			e.printStackTrace();
			messageExceptions(e);
		}
	}

	/**
	 * Registra as exceções no FacesMessage
	 * 
	 * @param exception
	 */
	private void messageExceptions(Exception e) {
		FacesContext context = getCurrentInstance();
		
		Throwable causa = e;
		causa = causa.getCause();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, causa.getMessage(), null));
        logger.log(Level.SEVERE, causa.getMessage(), causa);
	}
}
