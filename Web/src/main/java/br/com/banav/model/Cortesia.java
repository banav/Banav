package br.com.banav.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by gilson on 4/18/14.
 */
@Entity
@Table(name = "cortesia")
@SequenceGenerator(name = "CortesiaGenerator_SEQ", initialValue = 1, sequenceName = "seq_cortesia")
public class Cortesia implements Serializable {

    @Id
    @GeneratedValue(generator = "CortesiaGenerator_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;

    private String rg;

    private String cpf;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_criacao", nullable = false)
    private Date dataCriacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "viagem_id", nullable = false)
    private Viagem viagem;

    @ManyToOne
    @JoinColumn(name = "passagem_id")
    private Passagem passagem;
}
