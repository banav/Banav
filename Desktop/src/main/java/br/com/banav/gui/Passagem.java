package br.com.banav.gui;

import br.com.banav.dao.ClasseDAO;
import br.com.banav.gui.component.JButtonData;
import br.com.banav.model.Classe;
import br.com.banav.model.Viagem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by gilson on 4/1/14.
 */
public class Passagem extends JPanel {
    private JPanel mainContent;
    private JButton btVoltar;
    private JPanel panelPassagens;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JPanel inteiraPanel;
    private JPanel meiaPanel;
    private JPanel gratuidadePanel;
    private JTable tablePassagem;
    private JLabel labelQuantidade;
    private JLabel labelTotal;
    private JButton butFinalizar;

    public static final Integer INTEIRA = 0;
    public static final Integer MEIA = 1;
    public static final Integer GRATUIDADE = 2;

    private Main main;


    public Passagem(Main main) {
        this.main = main;

        setLayout(new BorderLayout());
        add(mainContent, BorderLayout.CENTER);

        iniciarTabelaPassagens();
    }

    public void carregar(Viagem viagem) {
        painelInteira();
        painelMeia();
        painelGratuidade();
    }

    private void painelMeia() {
        meiaPanel.removeAll();

        ClasseDAO classeDAO = new ClasseDAO();
        Classe economica = classeDAO.getClasseEconomica();

        JButtonData butClasse = new JButtonData(economica.getNome());
        butClasse.setData(economica);
        butClasse.setFont(new Font("Arial", Font.BOLD, 20));
        butClasse.addActionListener(new AddPassagemActionListener(this));

        meiaPanel.add(butClasse);
    }

    private void painelInteira() {
        inteiraPanel.removeAll();

        ClasseDAO classeDAO = new ClasseDAO();
        java.util.List<Classe> classes = classeDAO.listar();

        for (Classe classe : classes) {
            JButtonData butClasse = new JButtonData(classe.getNome());
            butClasse.setData(classe);
            butClasse.setFont(new Font("Arial", Font.BOLD, 20));
            butClasse.addActionListener(new AddPassagemActionListener(this));

            inteiraPanel.add(butClasse);
        }
    }

    private void painelGratuidade() {
        gratuidadePanel.removeAll();

        ClasseDAO classeDAO = new ClasseDAO();
        Classe economica = classeDAO.getClasseEconomica();

        JButtonData butClasse = new JButtonData(economica.getNome());
        butClasse.setData(economica);
        butClasse.setFont(new Font("Arial", Font.BOLD, 20));
        butClasse.addActionListener(new AddPassagemActionListener(this));

        gratuidadePanel.add(butClasse);
    }

    private void iniciarTabelaPassagens() {
        ArrayList<String> columns = new ArrayList<String>();
        ArrayList<String[]> values = new ArrayList<String[]>();

        columns.add("Tipo de Passagem");
        columns.add("Classe");
        columns.add("Valor");
        columns.add("Opções");

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        tablePassagem.setModel(tableModel);
        tablePassagem.setFont(new Font("Arial", Font.BOLD, 20));
        tablePassagem.setRowHeight(40);
        tablePassagem.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
    }

    private void addPassagem(Classe classe, Integer tipo) {
        String descricaoTipo = "";

        if(tipo.equals(MEIA)) {
            descricaoTipo = "Meia Passagem";
        } else if(tipo.equals(INTEIRA)) {
            descricaoTipo = "Inteira";
        } else if(tipo.equals(GRATUIDADE)) {
            descricaoTipo = "Gratuidade";
        }

        DefaultTableModel model = (DefaultTableModel) tablePassagem.getModel();
        model.addRow(new Object[]{classe.getNome(), descricaoTipo, "R$ 20,00", "Remover"});
    }

    private static class AddPassagemActionListener implements ActionListener {
        private final Passagem passagem;

        public AddPassagemActionListener(Passagem passagem) {
            this.passagem = passagem;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JButtonData butClasse = (JButtonData) actionEvent.getSource();
            Classe classe = (Classe) butClasse.getData();

            Integer tipo = null;
            if(butClasse.getParent().equals(passagem.meiaPanel)) {
                tipo = Passagem.MEIA;
            } else if(butClasse.getParent().equals(passagem.inteiraPanel)) {
                tipo = Passagem.INTEIRA;
            } else if(butClasse.getParent().equals(passagem.gratuidadePanel)) {
                tipo = Passagem.GRATUIDADE;
            }

            passagem.addPassagem(classe, tipo);
        }
    }

    private class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }

            setText((value == null) ? "" : value.toString());
            return this;
        }
    }
}
