package br.com.banav.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by GilsonRocha on 12/01/14.
 */

@Entity
@Table(name = "usuario")
@SequenceGenerator(name = "UsuarioGenerator_SEQ", initialValue = 1, sequenceName = "seq_usuario")
public class Usuario implements Serializable{

    @Id
    @GeneratedValue(generator = "UsuarioGenerator_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;

    private String login;

    private String senha;

    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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