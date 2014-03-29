package br.com.banav.gui;

import br.com.banav.dao.UsuarioDAO;
import br.com.banav.model.Usuario;
import br.com.banav.service.UsuarioSrv;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by GilsonRocha on 05/03/14.
 */
public class Login extends JFrame {
    private JTextField tfLogin;
    private JPasswordField tfSenha;
    private JButton btEntrar;
    private JPanel mainPanel;

    public Login() {
        setContentPane(mainPanel);
        setTitle("Acesso ao sistema");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        btEntrar.addActionListener(new EntrarListener(this));
    }

    public static void main(String args[]) {
        new Login();
    }

    private static class EntrarListener implements ActionListener {

        private Login login;

        private EntrarListener(Login login) {
            this.login = login;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            UsuarioDAO usuarioSrv = new UsuarioDAO();
            List<Usuario> usuarios = usuarioSrv.listar();
            for (Usuario usuario : usuarios) {
                System.out.println(usuario.getNome());
            }

            login.dispose();
            new Main();
        }
    }
}
