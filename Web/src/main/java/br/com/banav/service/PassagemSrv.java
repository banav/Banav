package br.com.banav.service;

import br.com.banav.dao.CortesiaDAO;
import br.com.banav.dao.PassagemDAO;
import br.com.banav.dao.PassagemHistoricoDAO;
import br.com.banav.model.Cortesia;
import br.com.banav.model.Passagem;
import br.com.banav.model.PassagemHistorico;
import br.com.banav.model.PassagemMovimento;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by GilsonRocha on 27/01/14.
 */
@Stateless
public class PassagemSrv {

    @Inject private PassagemDAO passagemDAO;
    @Inject private CortesiaDAO cortesiaDAO;

    @Inject
    private PassagemHistoricoDAO passagemHistoricoDAO;



    public List<Passagem> listar() {
        return passagemDAO.listar();
    }

    public void salvar(Passagem passagem, Long cortesia) throws Exception {

        Passagem _passagem = null;
        boolean existePassagemHistorico = false;

        try{
            _passagem = passagemDAO.buscarPorCodigoBarras(passagem.getCodigoBarras());

            if(_passagem != null){
                List<PassagemHistorico> passagemHistoricos = passagemHistoricoDAO.verificaPassagemHistorico(_passagem);

                for(PassagemHistorico ph : passagemHistoricos){
                    if(ph.getPassagemMovimento().equals(PassagemMovimento.MARCADA)){
                        existePassagemHistorico = true;
                        break;
                    }
                }
                if(!existePassagemHistorico){
                    PassagemHistorico  passagemHistorico = passagem.getHistorico().get(0);
                    passagemHistorico.setPassagem(_passagem);
                    passagemHistoricoDAO.salvar(passagemHistorico);
                }
            }
            else if(_passagem == null)

                passagemDAO.salvar(passagem);

                if(cortesia != null) {
                    Cortesia _cortesia = cortesiaDAO.getUm(cortesia, Cortesia.class);
                    _cortesia.setPassagem(passagem);
                    cortesiaDAO.atualizar(_cortesia);
                }

        }

        catch (Exception e){
            throw e;
        }

    }

    public void cancelarPassagem(String codigoBarras, Date dataCancelamento) throws Exception{

        Passagem _passagem = null;
        boolean existePassagemHistorico = false;

        try{
            _passagem = passagemDAO.buscarPorCodigoBarras(codigoBarras);

            if(_passagem != null){
                List<PassagemHistorico> passagemHistoricos = passagemHistoricoDAO.verificaPassagemHistorico(_passagem);
                for(PassagemHistorico ph : passagemHistoricos){
                    if(ph.getPassagemMovimento().equals(PassagemMovimento.CANCELADA)){
                        throw new Exception("Passagem ja Cancelada");

                    }
                }

                if(!existePassagemHistorico){
                    PassagemHistorico passagemHistorico = new PassagemHistorico();
                    passagemHistorico.setData(dataCancelamento);
                    passagemHistorico.setPassagem(_passagem);
                    passagemHistorico.setPassagemMovimento(PassagemMovimento.CANCELADA);
                    passagemHistoricoDAO.salvar(passagemHistorico);
                }
            }
            else{
                throw new Exception("Passagem não existe ou não sincronizada para a base de dados central!");
            }

        }
        catch (Exception e){
            throw e;
        }
    }
}
