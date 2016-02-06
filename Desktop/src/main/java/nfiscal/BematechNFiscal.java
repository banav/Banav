package nfiscal;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface BematechNFiscal extends Library {

    public BematechNFiscal Instance = (BematechNFiscal) Native.loadLibrary("mp2032", BematechNFiscal.class);


    public static final int ERRO_COMUNICACAO = 0;
    public static final int SUCESSO = 1;
    public static final int PARAMETRO_INVALIDO = -2;
    public static final int POUCO_PAPEL = 5;
    public static final int TAMPA_ABERTA = 9;
    public static final int FUNCIONANDO = 24;
    public static final int SEM_PAPEL = 32;

    public int ConfiguraModeloImpressora(int modelo);
    public int IniciaPorta(String porta);
    public int FechaPorta();
    public int FormataTX(String BufTras, int tipoletra, int italic, int sublin, int expand, int enfat);
    public int ImprimeBitmap(String bitmap, int orientacao);
    public int AcionaGuilhotina(int modo);
    public int ConfiguraCodigoBarras(int altura, int largura, int posicaoCaracteres, int fonte, int margem);
    public int ImprimeCodigoBarrasUPCA(String codigo);
    public int ImprimeCodigoBarrasEAN8(String codigo);
    public int ImprimeCodigoBarrasCODE39(String codigo);
    public int ImprimeCodigoBarrasCODE93(String codigo);
    public int ImprimeCodigoBarrasCODE128(String codigo);

    public int Le_Status();
}
