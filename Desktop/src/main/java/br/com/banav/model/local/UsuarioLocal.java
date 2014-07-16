package br.com.banav.model.local;

import br.com.banav.model.Endereco;
import br.com.banav.model.Perfil;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by GilsonRocha on 12/01/14.
 */

@Entity
@Table(name = "usuario", schema = "offline")
public class UsuarioLocal implements Serializable{

    @Id
    private Long id;

    private String nome;

    private String login;

    private String senha;

    @Basic
    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}