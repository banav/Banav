package br.com.banav.service;

import br.com.banav.dao.PagSeguroDAO;
import br.com.banav.dao.PassageiroDAO;
import br.com.banav.model.PagSeguro;
import br.com.banav.model.Passageiro;
import br.com.banav.model.ViagemValorClasse;
import br.com.banav.rest.dto.vendas.ValorClasseDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by gilson on 27/02/16.
 */
@Stateless
public class PagSeguroSrv {

    @Inject private PagSeguroDAO pagSeguroDAO;

    @Inject private PassageiroDAO passageiroDAO;

    public PagSeguro salvar(List<ValorClasseDTO> valorClassesDTO) {
        PagSeguro pagSeguro = getPagSeguro();
        pagSeguroDAO.salvar(pagSeguro);

        for (ValorClasseDTO valorClasseDTO : valorClassesDTO) {
            for (Passageiro passageiro : valorClasseDTO.getPassageiros()) {
                passageiro.setViagemValorClasse(new ViagemValorClasse(valorClasseDTO.getIdViagemValorClasse()));
                passageiro.setPagSeguro(pagSeguro);

                passageiroDAO.salvar(passageiro);
            }
        }

        return pagSeguro;
    }

    private PagSeguro getPagSeguro() {
        PagSeguro pagSeguro = new PagSeguro();
        pagSeguro.setAtivo(true);
        pagSeguro.setDataMovimentacao(new Date());
        return pagSeguro;
    }
}
