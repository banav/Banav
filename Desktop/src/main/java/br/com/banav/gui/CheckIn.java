package br.com.banav.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by GilsonRocha on 10/03/14.
 */
public class CheckIn extends JPanel {
    private JPanel mainPanel;
    private JTextField textField1;
    private JPanel panelSinalizador;

    public CheckIn() {
       // this.main = main;

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
    }
}
