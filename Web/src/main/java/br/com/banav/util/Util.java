package br.com.banav.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
}
