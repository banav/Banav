package br.com.banav.venda.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by gustavocosta on 04/08/14.
 */

@MappedSuperclass
public  abstract class EntidadeBasica implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataMovimentacao;

    @Basic
    protected Boolean ativo;


    public EntidadeBasica(){
        this.ativo = true;
    }

    @PrePersist
    @PreUpdate
    public void atualizaDataMovimentacao(){
        this.dataMovimentacao = new Date();
    }

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