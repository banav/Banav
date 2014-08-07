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
public class PassagemDTO implements Serializable {

    @XmlElement
    private Long id;

    @XmlElement
    private Long viagemValorClasseID;

    @XmlElement
    private String codigoBarras;

    @XmlElement
    private Double valor;

    @XmlElement
    private Boolean gratuidade;

    @XmlElement
    private Boolean checkin;

    @XmlElement
    private Date dataMovimentacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getViagemValorClasseID() {
        return viagemValorClasseID;
    }

    public void setViagemValorClasseID(Long viagemValorClasseID) {
        this.viagemValorClasseID = viagemValorClasseID;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Boolean getGratuidade() {
        return gratuidade;
    }

    public void setGratuidade(Boolean gratuidade) {
        this.gratuidade = gratuidade;
    }

    public Boolean getCheckin() {
        return checkin;
    }

    public void setCheckin(Boolean checkin) {
        this.checkin = checkin;
    }

    public Date getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }
}
