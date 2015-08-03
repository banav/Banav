package br.com.banav.venda.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by GilsonRocha on 12/01/14.
 */
@Entity
@Table(name = "passagem_historico")
@SequenceGenerator(name = "PassagemHistoricoGenerator_SEQ", initialValue = 1, sequenceName = "seq_pass_hist", allocationSize = 1, schema = "public")
public class PassagemHistorico implements Serializable{


    @Id
    @GeneratedValue(generator = "PassagemHistoricoGenerator_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "passagem_id")
    private Passagem passagem;

    @Basic
    @Enumerated(EnumType.STRING)
    private PassagemMovimento passagemMovimento;

    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Passagem getPassagem() {
        return passagem;
    }

    public void setPassagem(Passagem passagem) {
        this.passagem = passagem;
    }

    public PassagemMovimento getPassagemMovimento() {
        return passagemMovimento;
    }

    public void setPassagemMovimento(PassagemMovimento passagemMovimento) {
        this.passagemMovimento = passagemMovimento;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
