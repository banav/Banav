package nfiscal;

import br.com.banav.exception.ImpressoraError;
import br.com.banav.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Ticket {

    static {
        System.setProperty("jna.library.path", "C:\\Windows\\SysWOW64");
    }
	private static final Log log = LogFactory.getLog(Ticket.class);
	public static void imprimir(String origem, String destino, String data, String hora, String tipo, String valor, String codigoBarras, String nome) throws ImpressoraError{
		int iRetorno = 0;
		int iOpc = 1;

        origem = Util.removeAcentos(origem);
        destino = Util.removeAcentos(destino);

		BematechNFiscal cupom = BematechNFiscal.Instance;

        iRetorno = cupom.ConfiguraModeloImpressora(7);
        iRetorno = cupom.IniciaPorta("USB");

        iRetorno = cupom.Le_Status();

        if(iRetorno == BematechNFiscal.ERRO_COMUNICACAO)
            throw new ImpressoraError("Erro de Comunicação!");
        else if(iRetorno == BematechNFiscal.SEM_PAPEL)
            throw new ImpressoraError("Impressora sem Papel!");
        else if(iRetorno == BematechNFiscal.TAMPA_ABERTA)
            throw new ImpressoraError("Tampa Aberta!");


		iRetorno = cupom.FormataTX("          BANAV\r\n", 2, 0, 0, 1, 1);
		iRetorno = cupom.FormataTX("               CONTROLE DE EMBARQUE\r\n", 2, 0, 0, 0, 0);
		iRetorno = cupom.FormataTX("\r\n", 2, 0, 0, 0, 0);
		iRetorno = cupom.FormataTX("       ORIGEM:  " + origem.toUpperCase() + " \r\n", 2, 0, 0, 0, 0);
		iRetorno = cupom.FormataTX("       DESTINO: " + destino.toUpperCase() + " \r\n", 2, 0, 0, 0, 0);
		iRetorno = cupom.FormataTX("       DATA: " + data + " \r\n", 2, 0, 0, 0, 0);
		iRetorno = cupom.FormataTX("       HORA: " + hora + "\r\n", 2, 0, 0, 0, 0);
		iRetorno = cupom.FormataTX("       TIPO PAX: " + tipo.toUpperCase() + " \r\n", 2, 0, 0, 0, 0);
        if(nome != null) {
            iRetorno = cupom.FormataTX("       " + nome.toUpperCase() + " \r\n", 2, 0, 0, 0, 0);
        }
		//iRetorno = cupom.FormataTX("                   VALOR: R$ " + valor + " \r\n", 2, 0, 0, 0, 0);
		iRetorno = cupom.FormataTX("\r\r\r", 2, 0, 0, 0, 0);
		//cupom.ConfiguraCodigoBarras(50, 1, 1, 1, 200);
        cupom.ConfiguraCodigoBarras(100, 0, 2, 0, 180);
		//iRetorno = cupom.ImprimeCodigoBarrasEAN8(codigoBarras);
        iRetorno = cupom.ImprimeCodigoBarrasCODE128(codigoBarras);
		//System.out.println(iRetorno);
		iRetorno = cupom.FormataTX("\r\n", 1, 0, 0, 0, 0);
		iRetorno = cupom.FormataTX("                    BOA VIAGEM!\r\n\r\n\r\n", 2, 0, 0, 0, 0);
		iRetorno = cupom.AcionaGuilhotina(0);
		iRetorno = cupom.FechaPorta();


        if(iRetorno < 0){
            log.error("Erro de impressão. Passagem com codigo de barras " + codigoBarras);
            throw new ImpressoraError("Erro de impressão!");
        }

        switch (iRetorno){
            case BematechNFiscal.PARAMETRO_INVALIDO:
                log.error("Parametro Inválido!. Passagem com codigo de barras " + codigoBarras);
                throw new ImpressoraError("Parametro Inválido!");
            case BematechNFiscal.ERRO_COMUNICACAO:
                log.error("Erro de comunicação!. Passagem com codigo de barras " + codigoBarras);
                throw new ImpressoraError("Erro de comunicação!");
            case BematechNFiscal.SEM_PAPEL:
                log.error("Sem Papel!. Passagem com codigo de barras " + codigoBarras);
                throw new ImpressoraError("Sem Papel!");
        }

        log.info("Passagem impressa com sucesso. Codigo de Barras da Passagem " + codigoBarras);

	}
}
