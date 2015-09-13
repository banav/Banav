package br.com.banav.rest.dto;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by gustavocosta on 12/09/15.
 */
@XmlRootElement(name = "mapaarrecadacao")
@XmlAccessorType(XmlAccessType.FIELD)
public class MapaArrecadacaoDTO {

    @XmlElement
    private Long viagemId;

    @XmlElement
    private String usuarioLogin;

    @XmlElement
    private Double valor;

    @XmlList
    @XmlElement
    private List<MapaViagemDTO> mapaViagens;


    public Long getViagemId() {
        return viagemId;
    }

    public void setViagemId(Long viagemId) {
        this.viagemId = viagemId;
    }

    public String getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public List<MapaViagemDTO> getMapaViagens() {
        return mapaViagens;
    }

    public void setMapaViagens(List<MapaViagemDTO> mapaViagens) {
        this.mapaViagens = mapaViagens;
    }
}
