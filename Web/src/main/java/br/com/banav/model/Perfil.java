package br.com.banav.model;

import javax.xml.bind.annotation.XmlEnum;

/**
 * Created by GilsonRocha on 12/01/14.
 */

@XmlEnum(String.class)
public enum Perfil {
    ADMINISTRADOR, VENDEDOR, CHECKIN;
}
