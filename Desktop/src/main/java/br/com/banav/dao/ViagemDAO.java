package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.model.Usuario;
import br.com.banav.model.Viagem;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by GilsonRocha on 29/01/14.
 */
public class ViagemDAO extends DAO<Viagem> {

    public List<Viagem> listar() {
        Query query = getEM().createQuery("select v from Viagem as v order by v.horaSaida desc");
        return query.getResultList();
    }

    public List<Viagem> listarPorDestino(Long destino, String data) {
        Query query = getEM().createQuery("select v from Viagem v where v.destino.id = :destino and to_char(v.horaSaida, 'DD/MM/YYYY') = :data order by v.horaSaida desc");
        query.setParameter("destino", destino);
        query.setParameter("data", data);

        return query.getResultList();
    }

    @Override
    public void excluir(Class<Viagem> clazz, Object id) {
        if(autoCommit) {
            getEM().getTransaction().begin();
            Viagem viagem = getUm(clazz, id);
            viagem.setAtivo(false);

            getEM().getTransaction().commit();
        } else {
            Viagem viagem = getUm(clazz, id);
            viagem.setAtivo(false);
        }
    }
}
