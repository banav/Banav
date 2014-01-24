package br.com.banav.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by GilsonRocha on 12/01/14.
 */

@Entity
@Table(name = "Navio")
@SequenceGenerator(name = "NavioGenerator_SEQ", initialValue = 1, sequenceName = "seq_navio")
public class Navio implements Serializable{

    @Id
    @GeneratedValue(generator = "NavioGenerator_SEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "navio_id")
    private Long navioID;

    private String nome;

    @OneToMany(mappedBy = "navio", fetch = FetchType.LAZY)
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
}
