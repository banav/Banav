package br.com.banav.gui;

import br.com.banav.dao.PortoDAO;
import br.com.banav.dao.ViagemDAO;
import br.com.banav.gui.component.JButtonData;
import br.com.banav.model.Porto;
import br.com.banav.model.Viagem;
import br.com.banav.util.Util;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created by gilson on 3/29/14.
 */
public class DestinoViagem extends JPanel {
    private JPanel mainContent;
    private JPanel panelDestino;
    private JPanel panelViagens;
    private JButton btVoltar;
    private JPanel painelOpcoes;
    private JFormattedTextField tfData;
    private Main main;
    private Porto portoDestino;


    public DestinoViagem(Main main) {
        this.main = main;

        setLayout(new BorderLayout());
        add(mainContent, BorderLayout.CENTER);

        panelDestino.setLayout(new BoxLayout(panelDestino, BoxLayout.Y_AXIS));
        panelViagens.setLayout(new BoxLayout(panelViagens, BoxLayout.Y_AXIS));

        btVoltar.addActionListener(new VoltarActionListener(main));
        tfData.addActionListener(new AlterarDataActionListener(this));
    }

    public void carregarDestinos() {

        panelDestino.removeAll();

        JLabel labelDestinos = new JLabel("Destino:");
        labelDestinos.setFont(new Font("Arial", Font.BOLD, 28));
        panelDestino.add(labelDestinos);

        PortoDAO portoDAO = new PortoDAO();
        java.util.List<Porto> portos = portoDAO.listarDestinosAgendados();

        for (Porto porto : portos) {
            JButtonData butDestino = new JButtonData(porto.getNome());
            butDestino.setData(porto);
            butDestino.setFont(new Font("Arial", Font.BOLD, 28));
            butDestino.addActionListener(new DestinoActionListener(main, this));

            JPanel jSpace = new JPanel();
            jSpace.setPreferredSize(new Dimension(100, 20));

            panelDestino.add(jSpace);
            panelDestino.add(butDestino);
        }
    }

    public void carregarViagens(Porto destino) {
        portoDestino = destino;
        panelViagens.removeAll();

        JLabel labelViagens = new JLabel("Viagem:");
        labelViagens.setFont(new Font("Arial", Font.BOLD, 28));
        panelViagens.add(labelViagens);

        ViagemDAO viagemDAO = new ViagemDAO();
        List<Viagem> viagens = viagemDAO.listarPorDestino(portoDestino.getId(), tfData.getText());

        for (Viagem viagem : viagens) {
            JButtonData butViagem = new JButtonData(
                String.format("<html>Data: %s,<br>Saída: %s,<br>Chegada: %s,<br>Porto: %s,<br>Navio: %s</html>",
                    Util.dataFormatada(viagem.getHoraSaida()),
                    Util.horaFormatada(viagem.getHoraSaida()),
                    Util.horaFormatada(viagem.getHoraChegada()),
                    viagem.getOrigem().getNome(),
                    viagem.getNavio().getNome()
                )
            );
            butViagem.setData(viagem);
            butViagem.setFont(new Font("Arial", Font.BOLD, 24));
            butViagem.addActionListener(new ViagemActionListener(main));

            JPanel jSpace = new JPanel();
            jSpace.setPreferredSize(new Dimension(100, 20));

            panelViagens.add(jSpace);
            panelViagens.add(butViagem);
        }

        if(viagens == null || viagens.isEmpty()) {
            JPanel jSpace = new JPanel();
            jSpace.setPreferredSize(new Dimension(100, 20));

            JButton botao = new JButton("Nenhuma viagem encontrada.");
            botao.setEnabled(false);

            panelViagens.add(jSpace);
            panelViagens.add(botao);
        }

        panelViagens.revalidate();
        panelViagens.repaint();
    }

    public void carregarViagens() {
        carregarViagens(portoDestino);
    }

    private void createUIComponents() {
        try {
            SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
            final Calendar hoje = Calendar.getInstance();

            MaskFormatter formatter = new MaskFormatter("##/##/####");
            tfData = new JFormattedTextField(formatter);
            tfData.setColumns(10);
            tfData.setText(formatData.format(hoje.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static class VoltarActionListener implements ActionListener {
        private final Main main;

        public VoltarActionListener(Main main) {
            this.main = main;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            main.abrir(MenuPrincipal.class.getCanonicalName());
        }
    }

    private static class DestinoActionListener implements ActionListener {
        private final Main main;
        private final DestinoViagem destinoViagem;

        public DestinoActionListener(Main main, DestinoViagem destinoViagem) {
            this.main = main;
            this.destinoViagem = destinoViagem;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JButtonData butDestino = (JButtonData) actionEvent.getSource();
            Porto porto = (Porto) butDestino.getData();
            destinoViagem.carregarViagens(porto);
        }
    }

    private static class ViagemActionListener implements ActionListener {
        private final Main main;

        public ViagemActionListener(Main main) {
            this.main = main;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JButtonData butViagem = (JButtonData) actionEvent.getSource();
            Viagem viagem = (Viagem) butViagem.getData();

            main.abrirPassagens(viagem);
        }
    }

    private static class AlterarDataActionListener implements ActionListener {

        private final DestinoViagem destinoViagem;

        public AlterarDataActionListener(DestinoViagem destinoViagem) {
            this.destinoViagem = destinoViagem;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            destinoViagem.carregarViagens();
        }
    }
}
