package br.com.banav.gui;

import br.com.banav.model.Viagem;

import javax.swing.*;
import java.awt.*;

/**
 * Created by gilson on 4/1/14.
 */
public class Passagem extends JPanel {
    private JPanel mainContent;
    private JButton btVoltar;
    private JPanel panelPassagens;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JPanel inteiraPanel;
    private JPanel meiaPanel;
    private JPanel gratuidadePanel;
    private JTable tablePassagem;
    private JLabel labelQuantidade;
    private JLabel labelTotal;
    private JButton butFinalizar;

    private Main main;


    public Passagem(Main main) {
        this.main = main;

        setLayout(new BorderLayout());
        add(mainContent, BorderLayout.CENTER);
    }

    public void carregar(Viagem viagem) {

    }
}
