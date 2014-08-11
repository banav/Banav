package br.com.banav.model;

import br.com.banav.model.local.UsuarioLocal;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by GilsonRocha on 12/01/14.
 */
@Entity
@Table(name = "passagem", schema = "offline")
public class Passagem implements Serializable{

    @Id
    @SequenceGenerator(name = "PassagemGenerator_SEQ", initialValue = 1, allocationSize = 1, sequenceName = "offline.seq_passagem")
    @GeneratedValue(generator = "PassagemGenerator_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;


    @ManyToOne()
    @JoinColumn(name = "viagem_valor_classe_id")
    private ViagemValorClasse viagemValorClasse;

    @ManyToOne()
    @JoinColumn(name = "usuario_id")
    private UsuarioLocal usuario;

    private String codigoBarras;

    private Double valor;

    private Boolean gratuidade;

    private Boolean enviado;

    private Date dataMovimentacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ViagemValorClasse getViagemValorClasse() {
        return viagemValorClasse;
    }

    public void setViagemValorClasse(ViagemValorClasse viagemValorClasse) {
        this.viagemValorClasse = viagemValorClasse;
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

    public Boolean getEnviado() {
        return enviado;
    }

    public void setEnviado(Boolean enviado) {
        this.enviado = enviado;
    }

    public Date getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public UsuarioLocal getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioLocal usuario) {
        this.usuario = usuario;
    }

    @PrePersist
    public void trigger(){
        this.dataMovimentacao = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passagem passagem = (Passagem) o;

        if (id != null ? !id.equals(passagem.id) : passagem.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
