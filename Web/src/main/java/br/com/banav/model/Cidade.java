package br.com.banav.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by GilsonRocha on 12/01/14.
 */

@Entity
@Table(name = "cidade")
@SequenceGenerator(name = "CidadeGenerator_SEQ", initialValue = 1, allocationSize = 1, schema = "public", sequenceName = "seq_cidade")
public class Cidade implements Serializable {

    @Id
    @GeneratedValue(generator = "CidadeGenerator_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cidade cidade = (Cidade) o;

        if (id != null ? !id.equals(cidade.id) : cidade.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        if(estado != null) {
            return nome + " / " + estado.getNome();
        }

        return nome;
    }
}
