package br.com.banav.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by gilson on 6/21/14.
 */

@Entity
@Table(name = "checkin", schema = "offline")
public class CheckIn implements Serializable {

    @Id
    @Column(name = "codigo_barras", nullable = false, length = 100)
    private String codigoBarras;

    @Column(nullable = false)
    private Boolean enviado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "data_cadastro")
    private Date dataCadastro;

    @Column(name = "data_envio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEnvio;

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Boolean getEnviado() {
        return enviado;
    }

    public void setEnviado(Boolean enviado) {
        this.enviado = enviado;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckIn checkIn = (CheckIn) o;

        if (codigoBarras != null ? !codigoBarras.equals(checkIn.codigoBarras) : checkIn.codigoBarras != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return codigoBarras != null ? codigoBarras.hashCode() : 0;
    }
}