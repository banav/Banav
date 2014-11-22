package br.com.banav.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by GilsonRocha on 12/01/14.
 */

@Entity
@Table(name = "porto", schema = "offline")
public class Porto extends EntidadeBasica implements Serializable{

    @Id
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

        Porto porto = (Porto) o;

        if (id != null ? !id.equals(porto.id) : porto.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return nome;
    }
}