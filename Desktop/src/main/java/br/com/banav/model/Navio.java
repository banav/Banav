package br.com.banav.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by GilsonRocha on 12/01/14.
 */

@Entity
@Table(name = "navio", schema = "offline")
public class Navio extends EntidadeBasica implements Serializable{

    @Id
    @Column(name = "id")
    private Long navioID;

    private String nome;

    @OneToMany(mappedBy = "navio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<NavioClasse> classes;


    public Long getNavioID() {
        return navioID;
    }

    public void setNavioID(Long navioID) {
        this.navioID = navioID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<NavioClasse> getClasses() {
        return classes;
    }

    public void setClasses(List<NavioClasse> classes) {
        this.classes = classes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Navio navio = (Navio) o;

        if (navioID != null ? !navioID.equals(navio.navioID) : navio.navioID != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return navioID != null ? navioID.hashCode() : 0;
    }

    @Override
    public String toString() {
        return nome;
    }
}
