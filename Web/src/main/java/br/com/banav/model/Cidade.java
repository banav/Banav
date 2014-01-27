package br.com.banav.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by GilsonRocha on 12/01/14.
 */

@Entity
@Table(name = "cidade")
@SequenceGenerator(name = "CidadeGenerator_SEQ", initialValue = 1, sequenceName = "seq_cidade")
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
}
