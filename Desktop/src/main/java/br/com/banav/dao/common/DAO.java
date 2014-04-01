package br.com.banav.dao.common;

import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("unchecked")
public abstract class DAO<T> {

    protected static EntityManagerFactory emf;

    protected static EntityManager em;

    static { getEM(); }

    protected static EntityManager getEM() {
        if(emf == null) {
            emf = Persistence.createEntityManagerFactory("BanavPU");
        }

        if(em == null) {
            em = emf.createEntityManager();
        }

        return em;
    }

    public void salvar(T t) {
        getEM().getTransaction().begin();
        getEM().persist(t);
        getEM().getTransaction().commit();
    }

    public void atualizar(T t) {
        getEM().getTransaction().begin();
        getEM().merge(t);
        getEM().getTransaction().commit();
    }

    public void excluir(Class<T> clazz, Object id) {
        getEM().getTransaction().begin();
        getEM().remove(getUm(clazz, id));
        getEM().getTransaction().commit();
    }

    public T getUm(Class<T> clazz, Object id) {
        return (T) getEM().find(clazz, id);
    }

    public Query getQuery(String namedQuery) {
        return getEM().createNamedQuery(namedQuery);
    }

    public Connection getConnection() throws SQLException {
        Session session = (Session) getEM().getDelegate();
        SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
        ConnectionProvider cp = sfi.getConnectionProvider();
        return cp.getConnection();
    }

    /** Método que retorna uma data incial para uma consulta HQL, usando na clásula between
     * para obter os resultados corretos por conta dos campos do tipo datetime.
     * @param data
     * @return retorna a dataInicial preenchiada com horário default para consulta
     */
    public static Date getDataInicial(Date data) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * Método que retorna uma data final para uma consulta HQL, usando na clásula between
     * para obter os resultados corretos por conta dos campos do tipo datetime.
     * @param data
     * @return retorna a dataFinal preenchiada com horário default para consulta
     */
    public static Date getDataFinal(Date data) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }
}
