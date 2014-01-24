package br.com.banav.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by gustavocosta on 24/01/14.
 */
@Entity
@Table(name = "estado")
@SequenceGenerator(name = "EstadoGenerator_SEQ", initialValue = 1, sequenceName = "seq_estado")
public class Estado implements Serializable{

    @Id
    @GeneratedValue(generator = "EstadoGenerator_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;

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
}


