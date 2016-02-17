package br.com.banav.rest.dto.vendas;

import br.com.banav.model.ViagemValorClasse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gilson on 16/02/16.
 */
public class ResultadoConsultaDTO implements Serializable {

    private Date dataViagem;

    private String de;

    private String para;

    private List<ResultadoViagemDTO> resultadoViagensDTO;

    public ResultadoConsultaDTO(List<ViagemValorClasse> viagemValorClasseList) {
        resultadoViagensDTO = new ArrayList<ResultadoViagemDTO>();

        if(viagemValorClasseList.size() > 0) {
            dataViagem = viagemValorClasseList.get(0).getViagem().getHoraSaida();
            de = viagemValorClasseList.get(0).getViagem().getOrigem().getNome();
            para = viagemValorClasseList.get(0).getViagem().getDestino().getNome();
        }

        for (ViagemValorClasse viagemValorClasse : viagemValorClasseList) {
            addViagem(viagemValorClasse);
        }
    }

    private void addViagem(ViagemValorClasse viagemValorClasse) {
        for (ResultadoViagemDTO resultadoViagemDTO : resultadoViagensDTO) {
            if(resultadoViagemDTO.getIdViagem().equals(viagemValorClasse.getViagem().getId())) {
                resultadoViagemDTO.addValorClasse(viagemValorClasse);
                return;
            }
        }

        ResultadoViagemDTO resultadoViagemDTO = new ResultadoViagemDTO(viagemValorClasse);
        resultadoViagemDTO.addValorClasse(viagemValorClasse);

        resultadoViagensDTO.add(resultadoViagemDTO);
    }

    public Date getDataViagem() {
        return dataViagem;
    }

    public void setDataViagem(Date dataViagem) {
        this.dataViagem = dataViagem;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public List<ResultadoViagemDTO> getResultadoViagensDTO() {
        return resultadoViagensDTO;
    }

    public void setResultadoViagensDTO(List<ResultadoViagemDTO> resultadoViagensDTO) {
        this.resultadoViagensDTO = resultadoViagensDTO;
    }
}
