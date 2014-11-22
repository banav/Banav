package br.com.banav;

import br.com.banav.jobs.*;

/**
 * Created by gilson on 22/11/14.
 */
public class Main {

    public static void main(String args[]) {
        EnvioCheckInJob envioCheckInJob = new EnvioCheckInJob();
        envioCheckInJob.start();

        UsuariosJob usuariosJob = new UsuariosJob();
        usuariosJob.start();

        EnvioPassagemJob envioPassagemJob = new EnvioPassagemJob();
        envioPassagemJob.start();

        BaseJob baseJob = new BaseJob();
        baseJob.start();

        EnvioCancelamentoJob envioCancelamentoJob = new EnvioCancelamentoJob();
        envioCancelamentoJob.start();
    }
}
