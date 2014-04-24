package br.com.banav.gui;

import br.com.banav.dao.PassagemDAO;
import br.com.banav.model.*;
import br.com.banav.model.Passagem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

/**
 * Created by GilsonRocha on 10/03/14.
 */
public class CheckIn extends JPanel {
    private JPanel mainPanel;
    private JTextField tfCodigoBarras;
    private JLabel lbStatus;
    private Main main;

    public CheckIn(Main main) {
        this.main = main;

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        tfCodigoBarras.addKeyListener(new EnterKeyAdapter(this));
    }

    private static class EnterKeyAdapter extends KeyAdapter {

        private CheckIn checkIn;

        private EnterKeyAdapter(CheckIn checkIn) {
            this.checkIn = checkIn;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                PassagemDAO passagemDAO = new PassagemDAO();
                java.util.List<br.com.banav.model.Passagem> passagens = passagemDAO.listarPorCodigoBarras(checkIn.tfCodigoBarras.getText());

                if(passagens == null || passagens.isEmpty()) {
                    checkIn.lbStatus.setText("Passagem não encontrada. Tente digitar o código e tecle ENTER.");
                } else {
                    Passagem passagem = passagens.get(0);
                    passagem.setCheckin(true);

                    passagemDAO.atualizar(passagem);

                    checkIn.lbStatus.setText("Registrado com sucesso.");
                }

                checkIn.tfCodigoBarras.setText("");
                checkIn.tfCodigoBarras.requestFocus();
            }
        }
    }
}
