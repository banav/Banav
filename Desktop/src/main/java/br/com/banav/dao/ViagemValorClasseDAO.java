package br.com.banav.dao;

import br.com.banav.dao.common.DAO;
import br.com.banav.model.Classe;
import br.com.banav.model.Viagem;
import br.com.banav.model.ViagemValorClasse;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by GilsonRocha on 29/01/14.
 */
public class ViagemValorClasseDAO extends DAO<ViagemValorClasse> {

    public ViagemValorClasse getPor(Viagem viagem, Classe classe) {
        Query query = getEM().createQuery("select vvc from ViagemValorClasse vvc where vvc.viagem.id = :id");
        query.setParameter("id", viagem.getId());

        List<ViagemValorClasse> lista = query.getResultList();
        for (ViagemValorClasse viagemValorClasse : lista) {
            if(viagemValorClasse.getNavioClasse().getClasse().equals(classe)) {
                return viagemValorClasse;
            }
        }

        return null;
    }
}
