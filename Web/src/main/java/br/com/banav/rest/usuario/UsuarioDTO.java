package br.com.banav.rest.usuario;

import br.com.banav.model.Perfil;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by gustavocosta on 14/07/14.
 */
@XmlRootElement(name = "usuario")
@XmlType( propOrder={ "id", "nome", "login", "senha", "perfil", "time", "ativo" } )
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

    @XmlElement
    private Date time;

    @XmlElement
    private Boolean ativo;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Boolean getAtivo() {return ativo;}

    public void setAtivo(Boolean ativo) {this.ativo = ativo;}
}
