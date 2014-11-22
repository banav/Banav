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
public class NavioListDTO {

    @XmlElement(name = "navio")
    private List<NavioDTO> collection = null;

    public List<NavioDTO> getCollection() {
        return collection;
    }

    public void setCollection(List<NavioDTO> collection) {
        this.collection = collection;
    }
}