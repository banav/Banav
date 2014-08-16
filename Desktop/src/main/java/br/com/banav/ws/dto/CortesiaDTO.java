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
@XmlRootElement(name = "cortesia")
@XmlAccessorType(XmlAccessType.FIELD)
public class CortesiaDTO implements Serializable {

    @XmlElement
    private Long id;

    @XmlElement
    private String nome;

    @XmlElement
    private String rg;

    @XmlElement
    private String cpf;

    @XmlElement
    private String solicitante;

    @XmlElement
    private String autorizante;

    @XmlElement
    private Date dataCriacao;

    @XmlElement
    private Long usuario;

    @XmlElement
    private Long viagem;

    @XmlElement
    private String passagemCodigoBarras;

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getAutorizante() {
        return autorizante;
    }

    public void setAutorizante(String autorizante) {
        this.autorizante = autorizante;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public Long getViagem() {
        return viagem;
    }

    public void setViagem(Long viagem) {
        this.viagem = viagem;
    }

    public String getPassagemCodigoBarras() {
        return passagemCodigoBarras;
    }

    public void setPassagemCodigoBarras(String passagemCodigoBarras) {
        this.passagemCodigoBarras = passagemCodigoBarras;
    }
}
