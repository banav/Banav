package br.com.banav.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by GilsonRocha on 10/03/14.
 */
public class MenuPrincipal extends JPanel {
    private JPanel mainContent;
    private JButton btEmitirPassagem;
    private JButton btEfetuarCheckIn;
    private JButton upgradeButton;

    private Main main;

    public MenuPrincipal(Main main) {
        this.main = main;

        setLayout(new BorderLayout());
        add(mainContent, BorderLayout.CENTER);
        btEmitirPassagem.addActionListener(new EmitirPassagemActionListener(this));
        btEfetuarCheckIn.addActionListener(new CheckInActionListener(this));
    }

    private static class EmitirPassagemActionListener implements ActionListener {

        private MenuPrincipal menuPrincipal;

        private EmitirPassagemActionListener(MenuPrincipal menuPrincipal) {
            this.menuPrincipal = menuPrincipal;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            menuPrincipal.main.abrirDestinos();
        }
    }

    private static class CheckInActionListener implements ActionListener {

        private MenuPrincipal menuPrincipal;

        private CheckInActionListener(MenuPrincipal menuPrincipal) {
            this.menuPrincipal = menuPrincipal;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            menuPrincipal.main.abrir(CheckIn.class.getCanonicalName());
        }
    }
}
