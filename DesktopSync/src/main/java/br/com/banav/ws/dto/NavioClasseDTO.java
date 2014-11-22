package br.com.banav.ws.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by gustavocosta on 06/08/14.
 */
@XmlRootElement(name = "navioclasse")
@XmlAccessorType(XmlAccessType.FIELD)
public class NavioClasseDTO implements Serializable{

    @XmlElement
    private Long navioID;

    @XmlElement
    private Long classeID;

    @XmlElement
    private Date dataMovimentacao;

    @XmlElement
    private Integer quantidade;

    @XmlElement
    private Boolean ativo;

    public Long getNavioID() {
        return navioID;
    }

    public void setNavioID(Long navioID) {
        this.navioID = navioID;
    }

    public Long getClasseID() {
        return classeID;
    }

    public void setClasseID(Long classeID) {
        this.classeID = classeID;
    }

    public Date getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
