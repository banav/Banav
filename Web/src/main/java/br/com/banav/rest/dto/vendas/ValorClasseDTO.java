package br.com.banav.rest.dto.vendas;

import br.com.banav.model.Passageiro;

import java.util.List;

/**
 * Created by gilson on 16/02/16.
 */
public class ValorClasseDTO {

    private Long idViagemValorClasse;

    private Double valor;

    private String classe;

    private Integer quantidade;

    private Double subtotal;

    private List<Passageiro> passageiros;

    public Long getIdViagemValorClasse() {
        return idViagemValorClasse;
    }

    public void setIdViagemValorClasse(Long idViagemValorClasse) {
        this.idViagemValorClasse = idViagemValorClasse;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public List<Passageiro> getPassageiros() {
        return passageiros;
    }

    public void setPassageiros(List<Passageiro> passageiros) {
        this.passageiros = passageiros;
    }
}