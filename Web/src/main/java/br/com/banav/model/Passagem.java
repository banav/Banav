package br.com.banav.model;

import java.util.List;

/**
 * Created by GilsonRocha on 12/01/14.
 */
public class Passagem {
    private ViagemValorClasse viagemValorClasse;
    private String codigoBarras;
    private Double valor;
    private Boolean gratuidade;
    private List<PassagemHistorico> historico;
}
