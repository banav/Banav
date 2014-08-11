package br.com.banav.ws.dto;


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
public class PortoListDTO {

    @XmlElement(name = "porto")
    private List<PortoDTO> collection = null;

    public List<PortoDTO> getCollection() {
        return collection;
    }

    public void setCollection(List<PortoDTO> collection) {
        this.collection = collection;
    }
}