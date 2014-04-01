package br.com.banav.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by GilsonRocha on 12/01/14.
 */
@Entity
@Table(name = "navio_classe")
@IdClass(NavioClassePK.class)
public class NavioClasse implements Serializable{

    @Id
    @OneToOne
    @JoinColumn(name = "navio", referencedColumnName = "id")
    private Navio navio;

    @Id
    @OneToOne
    @JoinColumn(name = "classe", referencedColumnName = "id")
    private Classe classe;

    private Integer quantidade;

    public Navio getNavio() {
        return navio;
    }

    public void setNavio(Navio navio) {
        this.navio = navio;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}