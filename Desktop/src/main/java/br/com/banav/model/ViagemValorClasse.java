package br.com.banav.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by GilsonRocha on 12/01/14.
 */
@Entity
@Table(name = "viagem_valor_classe")
@SequenceGenerator(name = "ViagemValorClasseGenerator_SEQ", initialValue = 1, sequenceName = "seq_via_val_cla")
public class ViagemValorClasse implements Serializable{

    @Id
    @GeneratedValue(generator = "ViagemValorClasseGenerator_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "viagem_id")
    private Viagem viagem;

    @JoinColumns({
       @JoinColumn(name = "navio", referencedColumnName = "navio"),
       @JoinColumn(name = "classe", referencedColumnName = "classe")
    })
    @OneToOne
    private NavioClasse navioClasse;

    private Double valor;

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
}
