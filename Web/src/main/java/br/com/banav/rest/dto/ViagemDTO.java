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
public class ViagemDTO implements Serializable {

    @XmlElement
    private Long id;

    @XmlElement
    private Long navioID;

    @XmlElement
    private Long origemID;

    @XmlElement
    private Long destinoID;

    @XmlElement
    private Date horaSaida;

    @XmlElement
    private Date horaChegada;

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

    public Long getNavioID() {
        return navioID;
    }

    public void setNavioID(Long navioID) {
        this.navioID = navioID;
    }

    public Long getOrigemID() {
        return origemID;
    }

    public void setOrigemID(Long origemID) {
        this.origemID = origemID;
    }

    public Long getDestinoID() {
        return destinoID;
    }

    public void setDestinoID(Long destinoID) {
        this.destinoID = destinoID;
    }

    public Date getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(Date horaSaida) {
        this.horaSaida = horaSaida;
    }

    public Date getHoraChegada() {
        return horaChegada;
    }

    public void setHoraChegada(Date horaChegada) {
        this.horaChegada = horaChegada;
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
