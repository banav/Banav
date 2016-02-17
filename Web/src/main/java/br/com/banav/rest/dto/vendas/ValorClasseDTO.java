package br.com.banav.rest.dto.vendas;

/**
 * Created by gilson on 16/02/16.
 */
public class ValorClasseDTO {

    private Long idViagemValorClasse;

    private Double valor;

    private String classe;

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
}