package br.com.banav.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by GilsonRocha on 27/02/16.
 */

@Entity
@Table(name = "passageiro")
@SequenceGenerator(name = "PassageiroGenerator_SEQ", initialValue = 1, allocationSize = 1, schema = "public", sequenceName = "seq_passageiro")
public class Passageiro extends EntidadeBasica implements Serializable {

    @Id
    @GeneratedValue(generator = "PassageiroGenerator_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 11)
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "viagem_valor_classe_id", nullable = false)
    private ViagemValorClasse viagemValorClasse;

    @ManyToOne
    @JoinColumn(name = "pagseguro_id", nullable = false)
    private PagSeguro pagSeguro;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ViagemValorClasse getViagemValorClasse() {
        return viagemValorClasse;
    }

    public void setViagemValorClasse(ViagemValorClasse viagemValorClasse) {
        this.viagemValorClasse = viagemValorClasse;
    }

    public PagSeguro getPagSeguro() {
        return pagSeguro;
    }

    public void setPagSeguro(PagSeguro pagSeguro) {
        this.pagSeguro = pagSeguro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passageiro that = (Passageiro) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
