package br.com.banav.rest.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by gustavocosta on 06/08/14.
 */
@XmlRootElement(name = "viagem_valor_classe")
@XmlAccessorType(XmlAccessType.FIELD)
public class ViagemValorClasseDTO implements Serializable{

    @XmlElement
    private Long id;

    @XmlElement
    private Long viagemID;

    @XmlElement
    private Long classeID;

    @XmlElement
    private Long navioID;

    @XmlElement
    private Double valor;

    @XmlElement
    private Double valorMeia;

    @XmlElement
    private Boolean aceitaGratuidade;

    @XmlElement
    private Date dataMovimentacao;

    @XmlElement
    private Boolean ativo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getViagemID() {
        return viagemID;
    }

    public void setViagemID(Long viagemID) {
        this.viagemID = viagemID;
    }

    public Long getClasseID() {
        return classeID;
    }

    public void setClasseID(Long classeID) {
        this.classeID = classeID;
    }

    public Long getNavioID() {
        return navioID;
    }

    public void setNavioID(Long navioID) {
        this.navioID = navioID;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValorMeia() {
        return valorMeia;
    }

    public void setValorMeia(Double valorMeia) {
        this.valorMeia = valorMeia;
    }

    public Boolean getAceitaGratuidade() {
        return aceitaGratuidade;
    }

    public void setAceitaGratuidade(Boolean aceitaGratuidade) {
        this.aceitaGratuidade = aceitaGratuidade;
    }

    public Date getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
