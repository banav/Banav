package br.com.banav.model;

import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by gustavocosta on 04/08/14.
 */

@MappedSuperclass
public  class EntidadeBasica implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataMovimentacao;

    @Basic
    protected Boolean ativo;

    public Date getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}