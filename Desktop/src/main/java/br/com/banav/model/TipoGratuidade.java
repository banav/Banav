package br.com.banav.model;

import javax.persistence.*;

/**
 * Created by gilson on 4/18/14.
 */

@Entity
@Table(name = "tipo_gratuidade")
@SequenceGenerator(name = "TipoGratuidadeGenerator_SEQ", initialValue = 1, sequenceName = "seq_tipo_gratuidade")
public class TipoGratuidade {

    @Id
    @GeneratedValue(generator = "TipoGratuidadeGenerator_SEQ", strategy = GenerationType.SEQUENCE)
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

        TipoGratuidade that = (TipoGratuidade) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
