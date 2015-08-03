package br.com.banav.venda.model;

import java.io.Serializable;

/**
 * Created by gustavocosta on 24/01/14.
 */

public class NavioClassePK implements Serializable {

    private Long navio;

    private Long classe;

    public Long getNavio() {
        return navio;
    }

    public void setNavio(Long navio) {
        this.navio = navio;
    }

    public Long getClasse() {
        return classe;
    }

    public void setClasse(Long classe) {
        this.classe = classe;
    }
}
