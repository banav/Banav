package br.com.banav.gui;

import br.com.banav.dao.PassagemDAO;
import br.com.banav.dao.PassagemHistoricoDAO;
import br.com.banav.model.PassagemHistorico;
import br.com.banav.model.PassagemMovimento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

/**
 * Created by gilson on 5/14/14.
 */
public class CancelarPassagem extends JPanel {
    private JPanel mainContent;
    private JTextField tfCodigoBarras;
    private JLabel lbStatus;
    private JButton vtVoltar;
    private Main main;

    public CancelarPassagem(Main main) {
        this.main = main;

        setLayout(new BorderLayout());
        add(mainContent, BorderLayout.CENTER);
        tfCodigoBarras.addKeyListener(new EnterKeyAdapter(this));
        vtVoltar.addActionListener(new VoltarActionListener(this));
    }

    private static class EnterKeyAdapter extends KeyAdapter {

        private CancelarPassagem cancelarPassagem;

        private EnterKeyAdapter(CancelarPassagem cancelarPassagem) {
            this.cancelarPassagem = cancelarPassagem;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                PassagemHistoricoDAO passagemHistoricoDAO = new PassagemHistoricoDAO();
                PassagemDAO passagemDAO = new PassagemDAO();
                java.util.List<br.com.banav.model.Passagem> passagens = passagemDAO.listarPorCodigoBarras(cancelarPassagem.tfCodigoBarras.getText());

                if(passagens == null || passagens.isEmpty()) {
                    cancelarPassagem.lbStatus.setText("Passagem não encontrada. Tente digitar o código e tecle ENTER.");
                } else {
                    br.com.banav.model.Passagem passagem = passagens.get(0);

                    if(passagem.getCheckin()) {
                        cancelarPassagem.lbStatus.setText(String.format("Entrada %s com CheckIn já registrado.", passagem.getCodigoBarras()));
                    } else {
                        java.util.List<PassagemHistorico> historico = passagemHistoricoDAO.listarPor(passagem);
                        for (PassagemHistorico passagemHistorico : historico) {
                            if(passagemHistorico.getPassagemMovimento().equals(PassagemMovimento.CANCELADA)) {
                                cancelarPassagem.lbStatus.setText(String.format("Passagem %s já foi cancelada anteriormente.", passagem.getCodigoBarras()));
                                return;
                            }
                        }

                        PassagemHistorico passagemHistorico = new PassagemHistorico();
                        passagemHistorico.setData(Calendar.getInstance().getTime());
                        passagemHistorico.setPassagem(passagem);
                        passagemHistorico.setPassagemMovimento(PassagemMovimento.CANCELADA);

                        passagemHistoricoDAO.salvar(passagemHistorico);

                        cancelarPassagem.lbStatus.setText(String.format("Passagem %s cancelada com sucesso.", passagem.getCodigoBarras()));
                    }
                }

                cancelarPassagem.tfCodigoBarras.setText("");
                cancelarPassagem.tfCodigoBarras.requestFocus();
            }
        }
    }

    private static class VoltarActionListener implements ActionListener {

        private CancelarPassagem cancelarPassagem;

        private VoltarActionListener(CancelarPassagem cancelarPassagem) {
            this.cancelarPassagem = cancelarPassagem;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            cancelarPassagem.main.abrir(MenuPrincipal.class.getCanonicalName());
        }
    }
}
