package br.com.banav.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by gilson on 12/09/15.
 */
@Entity
@Table(name = "mapa_viagem", schema = "offline")
public class MapaViagem implements Serializable {

    @Id
    @SequenceGenerator(name = "MapaViagemGenerator_SEQ", initialValue = 1, allocationSize = 1, sequenceName = "offline.seq_mapa_viagem")
    @GeneratedValue(generator = "MapaViagemGenerator_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "viagem_valor_classe_id", referencedColumnName = "id", nullable = false)
    private ViagemValorClasse viagemValorClasse;

    @ManyToOne
    @JoinColumn(nullable = false, name = "mapa_arrecadacao_id", referencedColumnName = "id")
    private MapaArrecadacao mapaArrecadacao;

    @Column(nullable = false, name = "numeracao_cupom")
    private String numeracaoCupom;

    @Column(nullable = false, name = "quantidade")
    private Integer quantidade;

    @Column(nullable = false, name = "valor")
    private Double valor;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapaViagem that = (MapaViagem) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "MapaViagem{" +
                "id=" + id +
                ", viagemValorClasse=" + viagemValorClasse +
                ", numeracaoCupom='" + numeracaoCupom + '\'' +
                ", quantidade=" + quantidade +
                ", valor=" + valor +
                '}';
    }
}
