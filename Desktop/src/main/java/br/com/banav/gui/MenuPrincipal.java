package br.com.banav.gui;

import br.com.banav.model.Perfil;
import br.com.banav.model.UsuarioLocal;
import br.com.banav.util.Session;

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
    private JButton btCortesias;
    private JButton btCancelamento;
    private JButton btMapaArrecadacao;

    private Main main;

    public MenuPrincipal(Main main) {
        this.main = main;

        setLayout(new BorderLayout());
        add(mainContent, BorderLayout.CENTER);

        btEmitirPassagem.addActionListener(new EmitirPassagemActionListener(this));
        btEfetuarCheckIn.addActionListener(new CheckInActionListener(this));
        btCortesias.addActionListener(new CortesiasActionListener(this));
        btCancelamento.addActionListener(new CancelamentoActionListener(this));
        btMapaArrecadacao.addActionListener(new MapaArrecadacaoActionListener(this));

        UsuarioLocal usuario = (UsuarioLocal) Session.get("usuario");
        if(usuario.getPerfil().equals(Perfil.CHECKIN)) {
            btEmitirPassagem.setEnabled(false);
            btCortesias.setEnabled(false);
            btCancelamento.setEnabled(false);
            btMapaArrecadacao.setEnabled(false);
        }

        if(usuario.getPerfil().equals(Perfil.VENDEDOR)) {
            btEfetuarCheckIn.setEnabled(false);
        }
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

    private static class CortesiasActionListener implements ActionListener {

        private MenuPrincipal menuPrincipal;

        private CortesiasActionListener(MenuPrincipal menuPrincipal) {
            this.menuPrincipal = menuPrincipal;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            menuPrincipal.main.abrirCortesias();
        }
    }

    private static class CancelamentoActionListener implements ActionListener {

        private MenuPrincipal menuPrincipal;

        private CancelamentoActionListener(MenuPrincipal menuPrincipal) {
            this.menuPrincipal = menuPrincipal;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            menuPrincipal.main.abrir(CancelarPassagem.class.getCanonicalName());
        }
    }

    private static class MapaArrecadacaoActionListener implements ActionListener {

        private MenuPrincipal menuPrincipal;

        private MapaArrecadacaoActionListener(MenuPrincipal menuPrincipal) {
            this.menuPrincipal = menuPrincipal;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            menuPrincipal.main.abrirMapa();
        }
    }
}