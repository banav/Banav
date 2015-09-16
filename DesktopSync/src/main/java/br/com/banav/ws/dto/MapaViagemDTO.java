package br.com.banav.ws.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by gustavocosta on 12/09/15.
 */
@XmlRootElement(name = "mapaviagem")
@XmlAccessorType(XmlAccessType.FIELD)
public class MapaViagemDTO {

    @XmlElement
    private Long viagemValorClasseID;

    @XmlElement
    private String numeracaoCupom;

    @XmlElement
    private Integer quantidade;

    @XmlElement
    private Double valor;

    public Long getViagemValorClasseID() {
        return viagemValorClasseID;
    }

    public void setViagemValorClasseID(Long viagemValorClasseID) {
        this.viagemValorClasseID = viagemValorClasseID;
    }

    public String getNumeracaoCupom() {
        return numeracaoCupom;
    }

    public void setNumeracaoCupom(String numeracaoCupom) {
        this.numeracaoCupom = numeracaoCupom;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

}
