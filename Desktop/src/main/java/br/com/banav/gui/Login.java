package br.com.banav.gui;

import br.com.banav.dao.UsuarioDAO;
import br.com.banav.model.UsuarioLocal;
import br.com.banav.util.Session;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by GilsonRocha on 05/03/14.
 */
public class Login extends JFrame {
    private JTextField tfLogin;
    private JPasswordField tfSenha;
    private JButton btEntrar;
    private JPanel mainPanel;
    private JLabel status;

    public Login() {
        setContentPane(mainPanel);
        setTitle("Acesso ao sistema");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        btEntrar.addActionListener(new EntrarListener(this));
        tfSenha.addActionListener(new EntrarListener(this));
    }

    public static void main(String args[]) {
        System.setProperty("file.encoding", "UTF-8");
        new Login();
    }

    private static class EntrarListener implements ActionListener {

        private Login login;

        private EntrarListener(Login login) {
            this.login = login;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                login.status.setText("");

                if(login.tfLogin.getText().isEmpty()) {
                    login.status.setText("Por favor, informe o Login.");
                    login.tfLogin.requestFocus();
                }

                if(new String(login.tfSenha.getPassword()).isEmpty()) {
                    login.status.setText("Por favor, informe a Senha.");
                    login.tfSenha.requestFocus();
                }

                // Tenta fazer conexão local
                UsuarioDAO usuarioDAOLocal = new UsuarioDAO();
                UsuarioLocal usuarioLocal = usuarioDAOLocal.login(login.tfLogin.getText(), new String(login.tfSenha.getPassword()));
                if(usuarioLocal == null) {
                    login.status.setText("Usuário não encontrado.");
                } else {
                    Session.put("usuario", usuarioLocal);

                    //login.iniciarJobs();
                    login.dispose();

                    new Main();
                }

            } catch (UnsupportedEncodingException e1) {
                login.status.setText(e1.getMessage());
            } catch (NoSuchAlgorithmException e1) {
                login.status.setText(e1.getMessage());
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(login, "Não foi possível conectar com o servidor. Tente novamente. " + ex.getMessage());
            }
        }
    }
}
