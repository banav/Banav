package br.com.banav.ws;


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
public class UsuarioListDTO {

    @XmlElement(name = "usuario")
    private List<UsuarioDTO> collection = null;

    public List<UsuarioDTO> getCollection() {
        return collection;
    }

    public void setCollection(List<UsuarioDTO> collection) {
        this.collection = collection;
    }
}