package br.com.banav.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by GilsonRocha on 12/01/14.
 */
@Entity
@Table(name = "viagem_valor_classe")
@SequenceGenerator(name = "ViagemValorClasseGenerator_SEQ", initialValue = 1, allocationSize = 1, schema = "public", sequenceName = "seq_via_val_cla")
public class ViagemValorClasse extends EntidadeBasica implements Serializable {

    @Id
    @GeneratedValue(generator = "ViagemValorClasseGenerator_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "viagem_id")
    private Viagem viagem;

    @JoinColumns({
            @JoinColumn(name = "navio", referencedColumnName = "navio"),
            @JoinColumn(name = "classe", referencedColumnName = "classe")
    })
    @OneToOne(cascade = CascadeType.PERSIST)
    private NavioClasse navioClasse;

    private Double valor;

    @Column(name = "valor_meia")
    private Double valorMeia;

    @Column(name = "aceita_gratuidade", nullable = false)
    private Boolean aceitaGratuidade = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public NavioClasse getNavioClasse() {
        return navioClasse;
    }

    public void setNavioClasse(NavioClasse navioClasse) {
        this.navioClasse = navioClasse;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViagemValorClasse that = (ViagemValorClasse) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public ViagemValorClasse clone() {
        ViagemValorClasse _viagemValorClasse = new ViagemValorClasse();
        _viagemValorClasse.id = this.id;
        _viagemValorClasse.viagem = this.viagem;
        _viagemValorClasse.navioClasse = this.navioClasse;
        _viagemValorClasse.valor = this.valor;
        _viagemValorClasse.valorMeia = this.valorMeia;
        _viagemValorClasse.aceitaGratuidade = this.aceitaGratuidade;

        return _viagemValorClasse;
    }
}