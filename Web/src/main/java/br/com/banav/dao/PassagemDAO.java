package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.model.Passagem;
import br.com.banav.model.PassagemHistorico;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by GilsonRocha on 29/01/14.
 */
public class PassagemDAO extends DAO<Passagem> {

    public List<Passagem> listar() {
        Query query = getEm().createQuery("select p from Passagem as p order by p.id");
        return query.getResultList();
    }


    public Passagem buscarPorCodigoBarras(String codigoBarras) throws Exception{
        Passagem passagem = null;
        Query query = getEm().createQuery("select p from Passagem p where p.codigoBarras = :codigoBarras");
        query.setParameter("codigoBarras", codigoBarras);

        try{
            return (Passagem)query.getSingleResult();
        }
        catch (NoResultException e){
            return passagem;
        }
        catch (NonUniqueResultException e2){
            throw new Exception("Várias passagens com o mesmo codigo de barras. " +
                    "Favor contactar o administrador do sistema.");
        }
    }

    public Passagem efetuarCheckinCodigoBarras(String codigoBarras) throws Exception {
        Query query = getEm().createQuery("select p from Passagem p where p.codigoBarras = :codigoBarras");
        query.setParameter("codigoBarras", codigoBarras);

        Passagem passagem = null;

        try {
            passagem = (Passagem) query.getSingleResult();
        }
        catch (NoResultException e){
            throw new Exception("Passagem não existe na base de dados!");
        }
        catch (NonUniqueResultException e){
            throw new Exception("Varias passagem com o mesmo codigo de barras na base de dados!");
        }

        passagem.setCheckin(true);

        super.atualizar(passagem);

        return passagem;
    }


}