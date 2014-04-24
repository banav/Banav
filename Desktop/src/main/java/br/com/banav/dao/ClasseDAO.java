package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.model.Classe;
import br.com.banav.model.Viagem;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by gustavocosta on 01/02/14.
 */
public class ClasseDAO extends DAO<Classe> {

    public List<Classe> listar(){
        Query query = getEM().createQuery("select c from Classe c order by c.nome");
        return query.getResultList();
    }

    public List<Classe> getClassesMeiaPassagem(Viagem viagem) {
        Query query = getEM().createQuery("select vvc.navioClasse.classe from ViagemValorClasse vvc where vvc.viagem.id = :viagemId and vvc.valorMeia != null and vvc.valorMeia != 0");
        query.setParameter("viagemId", viagem.getId());

        return query.getResultList();
    }

    public List<Classe> getClassesGratuidade(Viagem viagem) {
        Query query = getEM().createQuery("select vvc.navioClasse.classe from ViagemValorClasse vvc where vvc.viagem.id = :viagemId and vvc.aceitaGratuidade = true");
        query.setParameter("viagemId", viagem.getId());

        return query.getResultList();
    }
}
