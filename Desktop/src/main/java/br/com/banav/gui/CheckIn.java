package br.com.banav.gui;

import br.com.banav.dao.CheckInDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

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
                try {
                    checkIn.lbStatus.setText("");

                    CheckInDAO checkInDAO = new CheckInDAO();

                    String _codigo = checkIn.tfCodigoBarras.getText();
                    if(checkInDAO.isValido(_codigo)) {
                        List<br.com.banav.model.CheckIn> checkIns = checkInDAO.listarPor(_codigo);
                        if(checkIns != null && !checkIns.isEmpty()) {
                            checkIn.lbStatus.setText(String.format("%s já registrado anteriormente.", _codigo));
                        } else {
                            br.com.banav.model.CheckIn novoCheckIn = new br.com.banav.model.CheckIn();
                            novoCheckIn.setDataCadastro(Calendar.getInstance().getTime());
                            novoCheckIn.setCodigoBarras(_codigo);
                            novoCheckIn.setEnviado(false);

                            checkInDAO.salvar(novoCheckIn);
                            checkIn.lbStatus.setText(String.format("%s registrado com sucesso.", _codigo));
                        }
                    } else {
                        checkIn.lbStatus.setText("Passagem ou Data inválida.");
                    }
                } catch (Exception ex) {
                    checkIn.lbStatus.setText("Erro. Tente fechar e depois abrir o sistema novamente.");
                } finally {
                    checkIn.tfCodigoBarras.setText("");
                    checkIn.tfCodigoBarras.requestFocus();
                }
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
