package br.com.banav.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by GilsonRocha on 12/01/14.
 */
@Entity
@Table(name = "classe")
@SequenceGenerator(name = "ClasseGenerator_SEQ", initialValue = 1, allocationSize = 1, schema = "public", sequenceName = "seq_classe")
public class Classe extends EntidadeBasica implements Serializable {


    @Id
    @GeneratedValue(generator = "ClasseGenerator_SEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long classeID;

    private String nome;

    public Long getClasseID() {
        return classeID;
    }

    public void setClasseID(Long classeID) {
        this.classeID = classeID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Classe classe = (Classe) o;

        if (classeID != null ? !classeID.equals(classe.classeID) : classe.classeID != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return classeID != null ? classeID.hashCode() : 0;
    }

    @Override
    public String toString() {
        return  this.nome;
    }
}
