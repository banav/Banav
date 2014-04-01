package br.com.banav.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by gilson on 3/29/14.
 */
public class DestinoViagem extends JPanel {
    private JPanel mainContent;
    private JPanel panelDestino;
    private JPanel panelViagens;
    private Main main;


    public DestinoViagem(Main main) {
        this.main = main;

        setLayout(new BorderLayout());
        add(mainContent, BorderLayout.CENTER);
    }

    public void carregar() {

    }
}
