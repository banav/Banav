package br.com.banav.model.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by gilson on 19/05/15.
 */
@Entity
public class DataValorDTO {

    @Id
    private Date data;

    private Double valor;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataValorDTO that = (DataValorDTO) o;

        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        return !(valor != null ? !valor.equals(that.valor) : that.valor != null);

    }

    @Override
    public int hashCode() {
        int result = data != null ? data.hashCode() : 0;
        result = 31 * result + (valor != null ? valor.hashCode() : 0);
        return result;
    }
}
