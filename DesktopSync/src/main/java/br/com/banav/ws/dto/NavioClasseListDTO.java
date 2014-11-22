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
public class NavioClasseListDTO {

    @XmlElement(name = "navioclasse")
    private List<NavioClasseDTO> collection = null;

    public List<NavioClasseDTO> getCollection() {
        return collection;
    }

    public void setCollection(List<NavioClasseDTO> collection) {
        this.collection = collection;
    }
}