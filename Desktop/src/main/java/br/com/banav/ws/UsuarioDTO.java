package br.com.banav.ws;

import br.com.banav.model.Perfil;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * Created by gustavocosta on 14/07/14.
 */
@XmlRootElement(name = "usuario")
@XmlType( propOrder={ "id", "nome", "login", "senha", "perfil" } )
@XmlAccessorType(XmlAccessType.FIELD)
public class UsuarioDTO implements Serializable {

    @XmlElement
    private Long id;

    @XmlElement
    private String login;

    @XmlElement
    private String nome;

    @XmlElement
    private String senha;

    @XmlElement
    private Perfil perfil;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}