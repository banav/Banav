package br.com.banav.service;

import br.com.banav.dao.PassagemDAO;
import br.com.banav.model.Passagem;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by Microsoft on 25/06/2014.
 */
@Stateless
public class CheckinSrv implements Serializable{

    @Inject
    private PassagemDAO passagemDAO;


    public Passagem efeturarCheckin(String codigoBarras) throws Exception{

       return passagemDAO.efetuarCheckinCodigoBarras(codigoBarras);

    }
}
