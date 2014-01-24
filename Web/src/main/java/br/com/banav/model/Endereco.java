package br.com.banav.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by gustavocosta on 24/01/14.
 */
@Entity
@Table(name = "endereco")
@SequenceGenerator(name = "EnderecoGenerator_SEQ", initialValue = 1, sequenceName = "seq_endereco")
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(generator = "EnderecoGenerator_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String logradouro;

    private String complemento;

    private String telefone;

    private String celular;

    private String cep;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade Cidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Cidade getCidade() {
        return Cidade;
    }

    public void setCidade(Cidade cidade) {
        Cidade = cidade;
    }
}
