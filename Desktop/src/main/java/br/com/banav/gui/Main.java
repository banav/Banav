package br.com.banav.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by GilsonRocha on 10/03/14.
 */
public class Main extends JFrame {
    private JPanel mainContent;
    private JPanel panelCard;

    private MenuPrincipal menuPrincipal;

    public Main() {
        setContentPane(mainContent);

        mainContent.add(panelCard, BorderLayout.CENTER);

        setTitle("Rodofluvial Banav LTDA");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);

        inicializarPaineis();
    }

    private void inicializarPaineis() {
        menuPrincipal = new MenuPrincipal(this);

        mainContent.add(menuPrincipal);

        //CardLayout cardLayout = (CardLayout) panelCard.getLayout();
        //cardLayout.show(panelCard, menuPrincipal.getClass().getCanonicalName());
    }
}
