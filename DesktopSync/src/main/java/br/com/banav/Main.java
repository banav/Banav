package br.com.banav;

import br.com.banav.jobs.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by gilson on 22/11/14.
 */
public class Main implements ActionListener {

    private EnvioCheckInJob envioCheckInJob = null;
    private UsuariosJob usuariosJob = null;
    private EnvioPassagemJob envioPassagemJob = null;
    private BaseJob baseJob = null;
    private EnvioCancelamentoJob envioCancelamentoJob = null;

    public Main() throws IOException {
        //Checa se tem suporte
        if (!SystemTray.isSupported()) {
            JOptionPane.showMessageDialog(null, "O Sistema Operacional não possui suporte ao ícone de bandeja. Entre em contato com o suporte.");
            System.exit(0);
        }

        criaTrayIcon();
    }

    private void criaTrayIcon() {
        //File sourceimage = new File("c:/DesktopSync/tray-icon.png");
        File sourceimage = new File("/home/gilson/Projetos/y2g/Banav/DesktopSync/src/main/java/br/com/banav/img/tray-icon.png");
        Image image2 = null;
        try {
            image2 = ImageIO.read(sourceimage);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar imagem do ícone. Entre em contato com o suporte.");
            System.exit(0);
        }

        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon = new TrayIcon(image2);
        final SystemTray tray = SystemTray.getSystemTray();

        MenuItem reiniciar = new MenuItem("Reiniciar");
        reiniciar.addActionListener(this);

        MenuItem sair = new MenuItem("Sair");
        sair.addActionListener(this);

        popup.add(reiniciar);
        popup.add(sair);

        trayIcon.setPopupMenu(popup);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível adicionar o ícone a bandeja do sistema. Entre em contato com o suporte.");
            System.exit(0);
        }
    }

    public void iniciarJobs() {
        System.out.println("Iniciando Jobs");
        if(envioCheckInJob == null) envioCheckInJob = new EnvioCheckInJob();
        envioCheckInJob.start();

        if(usuariosJob == null) usuariosJob = new UsuariosJob();
        usuariosJob.start();

        if(envioPassagemJob == null) envioPassagemJob = new EnvioPassagemJob();
        envioPassagemJob.start();

        if(baseJob == null) baseJob = new BaseJob();
        baseJob.start();

        if(envioCancelamentoJob == null) envioCancelamentoJob = new EnvioCancelamentoJob();
        envioCancelamentoJob.start();
    }

    public void reiniciarJobs() {
        envioCheckInJob = null;
        usuariosJob = null;
        envioPassagemJob = null;
        baseJob = null;
        envioCancelamentoJob = null;

        iniciarJobs();
    }

    public static void main(String args[]) throws IOException {
        Main main = new Main();
        main.iniciarJobs();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        MenuItem opcao = (MenuItem) actionEvent.getSource();

        if(opcao.getLabel().equals("Reiniciar")) {
            System.out.println("Reiniciando Jobs");
            reiniciarJobs();
        } else if(opcao.getLabel().equals("Sair")) {
            System.exit(0);
        }
    }
}
