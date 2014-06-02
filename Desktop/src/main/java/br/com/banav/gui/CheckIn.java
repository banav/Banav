package br.com.banav.gui;

import br.com.banav.dao.PassagemDAO;
import br.com.banav.model.*;
import br.com.banav.model.Passagem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by GilsonRocha on 10/03/14.
 */
public class CheckIn extends JPanel {
    private JPanel mainPanel;
    private JTextField tfCodigoBarras;
    private JLabel lbStatus;
    private JButton btVoltar;
    private Main main;

    public CheckIn(Main main) {
        this.main = main;

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        tfCodigoBarras.addKeyListener(new EnterKeyAdapter(this));
        tfCodigoBarras.requestFocus();
        btVoltar.addActionListener(new VoltarActionListener(this));
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
                    checkIn.lbStatus.setText("Passagem não encontrada.");
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    Passagem passagem = passagens.get(0);

                    final Date horaSaida = passagem.getViagemValorClasse().getViagem().getHoraSaida();

                    if(passagem.getCheckin()) {
                        checkIn.lbStatus.setText(String.format("Entrada %s já registrada anteriormente.", passagem.getCodigoBarras()));
                    } else if(!sdf.format(horaSaida).equals(sdf.format(new Date()))) {
                        checkIn.lbStatus.setText("Data irregular.");
                    } else {
                        passagem.setCheckin(true);
                        passagemDAO.atualizar(passagem);
                        checkIn.lbStatus.setText(String.format("Controle %s registrado com sucesso.", passagem.getCodigoBarras()));
                    }
                }

                checkIn.tfCodigoBarras.setText("");
                checkIn.tfCodigoBarras.requestFocus();
            }
        }
    }

    private static class VoltarActionListener implements ActionListener {

        private CheckIn checkIn;

        private VoltarActionListener(CheckIn checkIn) {
            this.checkIn = checkIn;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            checkIn.main.abrir(MenuPrincipal.class.getCanonicalName());
        }
    }
}
