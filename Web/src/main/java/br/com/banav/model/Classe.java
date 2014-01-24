package br.com.banav.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by GilsonRocha on 12/01/14.
 */
@Entity
@Table(name = "Classe")
@SequenceGenerator(name = "ClasseGenerator_SEQ", initialValue = 1, sequenceName = "seq_classe")
public class Classe implements Serializable {


    @Id
    @GeneratedValue(generator = "seq_classe", strategy = GenerationType.SEQUENCE)
    @Column(name = "classe_id")
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
}
