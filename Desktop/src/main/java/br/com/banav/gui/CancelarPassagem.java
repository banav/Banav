package br.com.banav.gui;

import br.com.banav.dao.PassagemCancelamentoDAO;
import br.com.banav.dao.PassagemDAO;
import br.com.banav.model.PassagemCancelamento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

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
                PassagemCancelamentoDAO passagemCancelamentoDAO = new PassagemCancelamentoDAO();
                List<PassagemCancelamento> passagemCancelamentoList = passagemCancelamentoDAO.listarPor(cancelarPassagem.tfCodigoBarras.getText());

                if(passagemCancelamentoList == null || passagemCancelamentoList.isEmpty()) {
                    PassagemCancelamento passagemCancelamento = new PassagemCancelamento();
                    passagemCancelamento.setEnviado(false);
                    passagemCancelamento.setCodigoBarras(cancelarPassagem.tfCodigoBarras.getText());
                    passagemCancelamento.setDataCadastro(new Date());
                    passagemCancelamento.setDataEnvio(null);

                    passagemCancelamentoDAO.salvar(passagemCancelamento);

                    cancelarPassagem.lbStatus.setText(String.format("Passagem %s cancelada.", cancelarPassagem.tfCodigoBarras.getText()));
                } else {
                    cancelarPassagem.lbStatus.setText(String.format("Passagem %s já foi cancelada anteriormente.", cancelarPassagem.tfCodigoBarras.getText()));
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