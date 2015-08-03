package br.com.banav.venda.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by gustavocosta on 24/01/14.
 */
@Entity
@Table(name = "estado")
@SequenceGenerator(name = "EstadoGenerator_SEQ", initialValue = 1, sequenceName = "seq_estado", schema = "public")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Estado estado = (Estado) o;

        if (id != null ? !id.equals(estado.id) : estado.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return  this.nome;
    }
}


