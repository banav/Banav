package br.com.banav.util;

import br.com.banav.model.Viagem;
import br.com.banav.model.UsuarioLocal;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by GilsonRocha on 26/12/13.
 */
public class Util {

    public static String toMD5(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if(senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser nula ou vazia.");
        }

        MessageDigest algorithm = MessageDigest.getInstance("MD5");
        byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }

        return hexString.toString();
    }

    public static String dataFormatada(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if(date != null) {
            return simpleDateFormat.format(date);
        } else {
            return simpleDateFormat.format(new Date());
        }
    }

    public static String horaFormatada(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

        if(date != null) {
            return simpleDateFormat.format(date);
        } else {
            return simpleDateFormat.format(new Date());
        }
    }

    public static String removeAcentos(String str) {

        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("[^\\p{ASCII}]", "");
        return str;

    }

    public static String gerarCodigoDeBarras(Viagem viagem, Integer sequencial, UsuarioLocal usuario){

        StringBuilder codigo = new StringBuilder();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(viagem.getHoraSaida());

        //calendar.get(Calendar.)

        String ano = Integer.toString(calendar.get(Calendar.YEAR)).substring(2);
        String mes = String.format("%02d",calendar.get(Calendar.MONTH) + 1);
        String dia = String.format("%02d",calendar.get(Calendar.DAY_OF_MONTH));
        String hora = String.format("%02d",calendar.get(Calendar.HOUR_OF_DAY));
        String minuto = String.format("%02d",calendar.get(Calendar.MINUTE));

        String user = String.format("%02d", usuario.getId());

        String origem = String.format("%02d",viagem.getOrigem().getId());
        String destino = String.format("%02d",viagem.getDestino().getId());
        String _sequencial = String.format("%04d", sequencial);




        codigo.append(ano);
        codigo.append(mes);
        codigo.append(dia);
        codigo.append(hora);
        codigo.append(minuto);
        codigo.append(user);
        codigo.append(origem);
        codigo.append(destino);
        codigo.append(_sequencial);
        //Ano -        14
        //Mes -        06
        //Dia -        20
        //Origem -     01
        //Destino -    02
        //Sequencial - 0001


        //201406200102010001
        return codigo.toString();
    }
}
