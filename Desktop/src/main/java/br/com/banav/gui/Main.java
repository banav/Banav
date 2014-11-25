package br.com.banav.gui;

import br.com.banav.model.Viagem;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by GilsonRocha on 10/03/14.
 */
public class Main extends JFrame {
    private JPanel mainContent;
    private JPanel panelCard;

    private MenuPrincipal menuPrincipal;
    private DestinoViagem destinoViagem;
    private Passagem passagem;
    private CheckIn checkIn;
    private CortesiaForm cortesiaForm;
    private CancelarPassagem cancelarPassagem;



    public Main() {
        setContentPane(mainContent);

        mainContent.add(panelCard, BorderLayout.CENTER);

        setTitle("Rodofluvial Banav LTDA");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);

        inicializarPaineis();

        try {
            String computerName = InetAddress.getLocalHost().getHostName();
            if(computerName != null && computerName.equalsIgnoreCase("gilson-note")) {
                Runtime.getRuntime().exec("java -jar /home/gilson/Projetos/y2g/Banav/DesktopSync/target/DesktopSync-1.0-SNAPSHOT.jar");
            } else {
                Runtime.getRuntime().exec("java -jar c:/DesktopSync/DesktopSync-1.0-SNAPSHOT.jar");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inicializarPaineis() {
        menuPrincipal = new MenuPrincipal(this);
        destinoViagem = new DestinoViagem(this);
        passagem = new Passagem(this);
        checkIn = new CheckIn(this);
        cortesiaForm = new CortesiaForm(this);
        cancelarPassagem = new CancelarPassagem(this);

        panelCard.add(menuPrincipal, menuPrincipal.getClass().getCanonicalName());
        panelCard.add(destinoViagem, destinoViagem.getClass().getCanonicalName());
        panelCard.add(passagem, passagem.getClass().getCanonicalName());
        panelCard.add(checkIn, checkIn.getClass().getCanonicalName());
        panelCard.add(cortesiaForm, cortesiaForm.getClass().getCanonicalName());
        panelCard.add(cancelarPassagem, cancelarPassagem.getClass().getCanonicalName());
    }

    public void abrir(String panel) {
        CardLayout cardLayout = (CardLayout) panelCard.getLayout();
        cardLayout.show(panelCard, panel);
    }

    public void abrirDestinos() {
        abrir(DestinoViagem.class.getCanonicalName());
        destinoViagem.carregarDestinos();
    }

    public void abrirPassagens(Viagem viagem) {
        abrir(Passagem.class.getCanonicalName());
        passagem.carregar(viagem);
    }

    public void abrirCortesias() {
        abrir(CortesiaForm.class.getCanonicalName());
        cortesiaForm.carregar();
    }
}
