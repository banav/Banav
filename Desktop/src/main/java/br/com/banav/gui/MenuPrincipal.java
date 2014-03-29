package br.com.banav.gui;

import javax.swing.*;
import java.awt.*;

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
    }
}
