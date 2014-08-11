package br.com.banav.exception;

import javax.persistence.PersistenceException;


public class ImpressoraError extends PersistenceException {

	private static final long serialVersionUID = -515133767490709254L;

	public ImpressoraError() {
		super("Erro ao acessar os dados!");
	}

    public ImpressoraError(String mensagem){
        super(mensagem);
    }
}