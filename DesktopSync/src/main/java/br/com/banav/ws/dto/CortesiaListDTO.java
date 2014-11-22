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
public class CortesiaListDTO {

    @XmlElement(name = "cortesia")
    private List<CortesiaDTO> collection = null;

    public List<CortesiaDTO> getCollection() {
        return collection;
    }

    public void setCollection(List<CortesiaDTO> collection) {
        this.collection = collection;
    }
}