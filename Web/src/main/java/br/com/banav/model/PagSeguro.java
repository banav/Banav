package br.com.banav.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by GilsonRocha on 12/01/14.
 */
@Entity
@Table(name = "pagseguro")
@SequenceGenerator(name = "PagSeguro_SEQ", initialValue = 1, allocationSize = 1, schema = "public", sequenceName = "seq_pagseguro")
public class PagSeguro extends EntidadeBasica implements Serializable {

    @Id
    @GeneratedValue(generator = "PagSeguro_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PagSeguro viagem = (PagSeguro) o;

        if (id != null ? !id.equals(viagem.id) : viagem.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
