package br.com.banav.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by GilsonRocha on 12/01/14.
 */
@Entity
@Table(name = "viagem")
@SequenceGenerator(name = "ViagemGenerator_SEQ", initialValue = 1, sequenceName = "seq_viagem")
public class Viagem implements Serializable {

    @Id
    @GeneratedValue(generator = "ViagemGenerator_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "navio_id")
    private Navio navio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "porto_origem")
    private Porto origem;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "porto_destino")
    private Porto destino;

    @Temporal(TemporalType.TIMESTAMP)
    private Date horaSaida;

    @Temporal(TemporalType.TIMESTAMP)
    private Date horaChegada;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Navio getNavio() {
        return navio;
    }

    public void setNavio(Navio navio) {
        this.navio = navio;
    }

    public Porto getOrigem() {
        return origem;
    }

    public void setOrigem(Porto origem) {
        this.origem = origem;
    }

    public Porto getDestino() {
        return destino;
    }

    public void setDestino(Porto destino) {
        this.destino = destino;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Viagem viagem = (Viagem) o;

        if (id != null ? !id.equals(viagem.id) : viagem.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
