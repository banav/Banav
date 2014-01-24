package br.com.banav.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by GilsonRocha on 12/01/14.
 */

@Entity
@Table(name = "Porto")
@SequenceGenerator(name = "PortoGenerator_SEQ", initialValue = 1, sequenceName = "seq_porto")
public class Porto implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PortoGenerator_SEQ")
    private Long id;

    private String nome;

    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
