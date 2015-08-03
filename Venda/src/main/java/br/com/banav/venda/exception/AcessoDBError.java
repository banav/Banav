package br.com.banav.venda.exception;

import javax.persistence.PersistenceException;


public class AcessoDBError extends PersistenceException {

	private static final long serialVersionUID = -515133767490709254L;

	public AcessoDBError() {
		super("Erro ao acessar os dados!");
	}
}