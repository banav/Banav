package nfiscal;

public class Ticket {

    static {
        System.setProperty("jna.library.path", "C:\\Windows\\SysWOW64");
    }
	
	public static void imprimir(String origem, String destino, String data, String hora, String tipo, String valor, String codigoBarras) {
		int iRetorno;
		int iOpc = 1;
		
		BematechNFiscal cupom = BematechNFiscal.Instance;
		
		iRetorno = cupom.ConfiguraModeloImpressora(7);
		iRetorno = cupom.IniciaPorta("USB");
		iRetorno = cupom.FormataTX("          BANAV\r\n", 2, 0, 0, 1, 1);
		iRetorno = cupom.FormataTX("               CONTROLE DE EMBARQUE\r\n", 2, 0, 0, 0, 0);
		iRetorno = cupom.FormataTX("\r\n", 2, 0, 0, 0, 0);
		iRetorno = cupom.FormataTX("                  ORIGEM:   " + origem + " \r\n", 2, 0, 0, 0, 0);
		iRetorno = cupom.FormataTX("                  DESTINO: " + destino + " \r\n", 2, 0, 0, 0, 0);
		iRetorno = cupom.FormataTX("                  DATA: " + data + " \r\n", 2, 0, 0, 0, 0);
		iRetorno = cupom.FormataTX("                    HORA: " + hora + "\r\n", 2, 0, 0, 0, 0);
		iRetorno = cupom.FormataTX("                    TIPO PAX: " + tipo + " \r\n", 2, 0, 0, 0, 0);
		iRetorno = cupom.FormataTX("                   VALOR: R$ " + valor + " \r\n", 2, 0, 0, 0, 0);
		iRetorno = cupom.FormataTX("\r\r\r", 2, 0, 0, 0, 0);
		cupom.ConfiguraCodigoBarras(50, 1, 0, 1, 200);
		iRetorno = cupom.ImprimeCodigoBarrasEAN8(codigoBarras);
		System.out.println(iRetorno);
		iRetorno = cupom.FormataTX("\r\n", 1, 0, 0, 0, 0);
		iRetorno = cupom.FormataTX("                    BOA VIAGEM!\r\n\r\n\r\n", 2, 0, 0, 0, 0);
		iRetorno = cupom.AcionaGuilhotina(0);
		iRetorno = cupom.FechaPorta();
	}
}
