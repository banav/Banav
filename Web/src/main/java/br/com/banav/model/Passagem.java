package br.com.banav.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by GilsonRocha on 12/01/14.
 */
@Entity
@Table(name = "passagem", uniqueConstraints = @UniqueConstraint(name = "uc_codigobarras", columnNames = {"codigobarras"}))
@SequenceGenerator(name = "PassagemGenerator_SEQ", initialValue = 1, allocationSize = 1, schema = "public", sequenceName = "seq_passagem")
public class Passagem implements Serializable{

    @Id
    @GeneratedValue(generator = "PassagemGenerator_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;


    @ManyToOne()
    @JoinColumn(name = "viagem_valor_classe_id")
    private ViagemValorClasse viagemValorClasse;

    private String codigoBarras;
    private Double valor;
    private Boolean gratuidade;

    @OneToMany(mappedBy = "passagem", cascade = CascadeType.PERSIST)
    private List<PassagemHistorico> historico;

    private Boolean checkin;

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

    public List<PassagemHistorico> getHistorico() {
        return historico;
    }

    public void setHistorico(List<PassagemHistorico> historico) {
        this.historico = historico;
    }

    public Boolean getCheckin() {
        return checkin;
    }

    public void setCheckin(Boolean checkin) {
        this.checkin = checkin;
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
