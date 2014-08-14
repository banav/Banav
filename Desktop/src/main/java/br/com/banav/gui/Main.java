package br.com.banav.gui;

import br.com.banav.gui.jobs.BaseJob;
import br.com.banav.gui.jobs.EnvioCheckInJob;
import br.com.banav.gui.jobs.EnvioPassagemJob;
import br.com.banav.gui.jobs.UsuariosJob;
import br.com.banav.model.Viagem;

import javax.swing.*;
import java.awt.*;

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

    private EnvioCheckInJob envioCheckInJob;
    private UsuariosJob usuariosJob;
    private EnvioPassagemJob envioPassagemJob;
    private BaseJob baseJob;

    public Main() {
        setContentPane(mainContent);

        mainContent.add(panelCard, BorderLayout.CENTER);

        setTitle("Rodofluvial Banav LTDA");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);

        inicializarPaineis();
        iniciarJobs();
    }

    public void pausarJobs() {
        this.envioCheckInJob.esperar();
        this.usuariosJob.esperar();
        this.envioPassagemJob.esperar();
        this.baseJob.esperar();

        this.envioCheckInJob = null;
        this.usuariosJob = null;
        this.envioPassagemJob = null;
        this.baseJob = null;
    }

    public void iniciarJobs() {
        if(envioCheckInJob == null) {
            envioCheckInJob = new EnvioCheckInJob();
        }

        if(!envioCheckInJob.isAlive()) {
            envioCheckInJob.start();
        }

        if(envioPassagemJob == null) {
            envioPassagemJob = new EnvioPassagemJob();
        }

        if(!envioPassagemJob.isAlive()) {
            envioPassagemJob.start();
        }

        if(usuariosJob == null) {
            usuariosJob = new UsuariosJob();
        }

        if(!usuariosJob.isAlive()) {
            usuariosJob.start();
        }

        if(baseJob == null){
            baseJob = new BaseJob();
        }

        if(!baseJob.isAlive()){
            baseJob.start();
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
