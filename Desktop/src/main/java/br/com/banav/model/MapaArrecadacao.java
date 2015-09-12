package br.com.banav.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by gilson on 12/09/15.
 */
@Entity
@Table(name = "mapa_arrecadacao", schema = "offline")
public class MapaArrecadacao implements Serializable {

    @Id
    @SequenceGenerator(name = "MapaArrecadacaoGenerator_SEQ", initialValue = 1, allocationSize = 1, sequenceName = "offline.seq_mapa_arrecadacao")
    @GeneratedValue(generator = "MapaArrecadacaoGenerator_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "viagem_id")
    private Viagem viagem;

    @ManyToOne()
    @JoinColumn(name = "usuario_id")
    private UsuarioLocal usuario;

    @OneToMany(mappedBy = "mapaArrecadacao")
    private List<MapaViagem> mapaViagem;

    @Column(nullable = false, name = "valor")
    private Double valor;

    @Column(nullable = false)
    private Boolean enviado;

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

    public UsuarioLocal getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioLocal usuario) {
        this.usuario = usuario;
    }

    public List<MapaViagem> getMapaViagem() {
        return mapaViagem;
    }

    public void setMapaViagem(List<MapaViagem> mapaViagem) {
        this.mapaViagem = mapaViagem;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Boolean getEnviado() {
        return enviado;
    }

    public void setEnviado(Boolean enviado) {
        this.enviado = enviado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapaArrecadacao that = (MapaArrecadacao) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "MapaArrecadacao{" +
                "id=" + id +
                ", viagem=" + viagem +
                ", usuario=" + usuario +
                ", mapaViagem=" + mapaViagem +
                ", valor=" + valor +
                ", enviado=" + enviado +
                '}';
    }
}