package br.com.banav.rest.dto.vendas;

import br.com.banav.model.ViagemValorClasse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gilson on 16/02/16.
 */
public class ResultadoViagemDTO {

    private Long idViagem;

    private Date horaSaida;

    private String navio;

    private List<ValorClasseDTO> valorClassesDTO;

    public ResultadoViagemDTO(ViagemValorClasse viagemValorClasse) {
        idViagem = viagemValorClasse.getViagem().getId();
        horaSaida = viagemValorClasse.getViagem().getHoraSaida();
        navio = viagemValorClasse.getViagem().getNavio().getNome();
    }

    public void addValorClasse(ViagemValorClasse viagemValorClasse) {
        if(valorClassesDTO == null) {
            valorClassesDTO = new ArrayList<ValorClasseDTO>();
        }

        ValorClasseDTO valorClasseDTO = new ValorClasseDTO();
        valorClasseDTO.setClasse(viagemValorClasse.getNavioClasse().getClasse().getNome());
        valorClasseDTO.setIdViagemValorClasse(viagemValorClasse.getId());
        valorClasseDTO.setValor(viagemValorClasse.getValor());

        valorClassesDTO.add(valorClasseDTO);
    }

    public Long getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(Long idViagem) {
        this.idViagem = idViagem;
    }

    public Date getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(Date horaSaida) {
        this.horaSaida = horaSaida;
    }

    public String getNavio() {
        return navio;
    }

    public void setNavio(String navio) {
        this.navio = navio;
    }

    public List<ValorClasseDTO> getValorClassesDTO() {
        return valorClassesDTO;
    }

    public void setValorClassesDTO(List<ValorClasseDTO> valorClassesDTO) {
        this.valorClassesDTO = valorClassesDTO;
    }
}
