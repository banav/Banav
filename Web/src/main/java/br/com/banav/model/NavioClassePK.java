package br.com.banav.model;

import java.io.Serializable;

/**
 * Created by gustavocosta on 24/01/14.
 */

public class NavioClassePK implements Serializable {

    private Long navioID;

    private Long classeID;

    public Long getNavioID() {
        return navioID;
    }

    public void setNavioID(Long navioID) {
        this.navioID = navioID;
    }

    public Long getClasseID() {
        return classeID;
    }

    public void setClasseID(Long classeID) {
        this.classeID = classeID;
    }
}
