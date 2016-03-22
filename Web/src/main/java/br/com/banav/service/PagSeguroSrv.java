package br.com.banav.service;

import br.com.banav.dao.PagSeguroDAO;
import br.com.banav.dao.PassageiroDAO;
import br.com.banav.model.PagSeguro;
import br.com.banav.model.Passageiro;
import br.com.banav.model.ViagemValorClasse;
import br.com.banav.rest.dto.vendas.ValorClasseDTO;
import br.com.uol.pagseguro.domain.Item;
import br.com.uol.pagseguro.domain.Sender;
import br.com.uol.pagseguro.domain.checkout.Checkout;
import br.com.uol.pagseguro.enums.Currency;
import br.com.uol.pagseguro.enums.DocumentType;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.properties.PagSeguroConfig;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
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

        try {
            gerarCheckoutPagSeguro(valorClassesDTO, pagSeguro);
        } catch (PagSeguroServiceException e) {
            e.printStackTrace();
        }

        return pagSeguro;
    }

    private String gerarCheckoutPagSeguro(List<ValorClasseDTO> valorClassesDTO, PagSeguro pagSeguro) throws PagSeguroServiceException {
        Checkout checkout = new Checkout();

        for(ValorClasseDTO dto : valorClassesDTO){
            Item item = new Item();
            item.setId(dto.getIdViagemValorClasse().toString());
            item.setQuantity(dto.getQuantidade());
            item.setAmount(new BigDecimal(String.format("%1$,.2f", dto.getValor())));
            item.setDescription(dto.getClasse());

            checkout.addItem(item);

        }

        //Informacoes do responsavel da compra
        checkout.setSender(
                "Gustavo Costa", // Nome
                "gustavocosta.ti@gmail.com", // email
                "91", // DDD
                "981233219", // Telefone
                DocumentType.CNPJ, // Tipo Documento
                "756.311.482-34" //  Numero documento
        );

        checkout.setCurrency(Currency.BRL);

        checkout.setReference(pagSeguro.getId().toString());

        // URL para onde o comprador será redirecionado (GET) após o fluxo de pagamento
        checkout.setRedirectURL("http://localhost:8080/thankyou");

        // URL para onde serão enviadas notificações (POST) indicando alterações no status da transação
        checkout.setNotificationURL("http://localhost:8080/notifications");


        boolean onlyCheckoutCode = true;
        String response = checkout.register(PagSeguroConfig.getAccountCredentials(), onlyCheckoutCode);

        //https://sandbox.pagseguro.uol.com.br/v2/checkout/payment.html?code=<<Mudar para o codigo retornado>>
        System.out.println("https://sandbox.pagseguro.uol.com.br/v2/checkout/payment.html?code="+ response);
        return response;
    }

    private PagSeguro getPagSeguro() {
        PagSeguro pagSeguro = new PagSeguro();
        pagSeguro.setAtivo(true);
        pagSeguro.setDataMovimentacao(new Date());
        return pagSeguro;
    }
}
