package br.com.banav.ws.dto;


import br.com.banav.rest.dto.ViagemValorClasseDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by gilson on 7/16/14.
 */
@XmlRootElement(name = "collection")
@XmlAccessorType(XmlAccessType.FIELD)
public class ViagemValorClasseListDTO {

    @XmlElement(name = "viagem_valor_classe")
    private List<ViagemValorClasseDTO> collection = null;

    public List<ViagemValorClasseDTO> getCollection() {
        return collection;
    }

    public void setCollection(List<ViagemValorClasseDTO> collection) {
        this.collection = collection;
    }
}