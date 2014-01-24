package br.com.banav.dao.common;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.banav.exception.AcessoDBError;

@SuppressWarnings("unchecked")
public abstract class DAO<T> implements DAOLocal<T> {

	@PersistenceContext(unitName = "BanavPU")
	private EntityManager em;

	@Override
	public void salvar(T entity) throws AcessoDBError {
		em.persist(entity);
	}

	@Override
	public void atualizar(T entity) throws AcessoDBError {
		em.merge(entity);
	}

	@Override
	public void remover(Serializable id, Class<T> clazz) throws AcessoDBError {
		em.remove(getUm(id, clazz));
	}

	@Override
	public T getUm(Serializable id, Class<T> clazz) throws AcessoDBError {
		T retorno = (T) em.find(clazz, id);
		return retorno;
	}

	@Override
	public List<T> listar(Class<T> clazz) throws AcessoDBError {
		return em.createQuery("Select t from " + clazz.getSimpleName() + " t").getResultList();
	}

	public EntityManager getEm() {
		return em;
	}
}
