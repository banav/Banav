package br.com.banav.model;

import javax.persistence.*;

/**
 * Created by GilsonRocha on 12/01/14.
 */
@Entity
@Table
public class Usuario {

    @Id
    @SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    private Integer id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Transient
    private Perfil perfil;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}