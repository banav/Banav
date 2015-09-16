package br.com.banav.gui;

import br.com.banav.dao.ViagemDAO;
import br.com.banav.dao.ViagemValorClasseDAO;
import br.com.banav.model.Viagem;
import br.com.banav.model.ViagemValorClasse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by gilson on 12/09/15.
 */
public class MapaArrecadacaoForm extends JPanel {

    private JPanel mainContent;
    private JButton btVoltar;
    private JFormattedTextField tfData;
    private JComboBox cbViagens;
    private JTable tableMapa;
    private JButton btEnviarMapa;

    private ArrayList<String[]> values = new ArrayList<String[]>();
    private ArrayList<String> columns = new ArrayList<String>();

    private Main main;

    public MapaArrecadacaoForm(Main main) {
        this.main = main;

        setLayout(new BorderLayout());
        add(mainContent, BorderLayout.CENTER);

        columns.add("Classe");
        columns.add("Controle de Cupom");
        columns.add("Quantidade");
        columns.add("Valor em R$");

        tableMapa.setFont(new Font("Arial", Font.BOLD, 20));
        tableMapa.setRowHeight(40);
        tableMapa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        btVoltar.addActionListener(new VoltarActionListener(this));
        tfData.addActionListener(new AlterarDataActionListener(this));
        cbViagens.addActionListener(new AlteraComboActionListener(this));
    }

    public void carregar() {
        carregarComboViagem();
        carregarTabela();
    }

    private void carregarTabela() {
        if(cbViagens.getItemCount() > 0) {
            Viagem viagemSelecionada = (Viagem) cbViagens.getSelectedItem();
            ViagemValorClasseDAO viagemValorClasseDAO = new ViagemValorClasseDAO();
            List<ViagemValorClasse> viagemValorClasseList = viagemValorClasseDAO.getPor(viagemSelecionada);

            TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
            tableMapa.setModel(tableModel);

            DefaultTableModel model = (DefaultTableModel) tableMapa.getModel();
            for (ViagemValorClasse viagemValorClasse : viagemValorClasseList) {
                model.addRow(new Object[]{ viagemValorClasse.getNavioClasse().getClasse().getNome() + " - Inteira", "", "", "" });

                if(viagemValorClasse.getValorMeia() != null && viagemValorClasse.getValorMeia() > 0) {
                    model.addRow(new Object[]{ viagemValorClasse.getNavioClasse().getClasse().getNome() + " - Meia", "", "", "" });
                }

                if(viagemValorClasse.getAceitaGratuidade()) {
                    model.addRow(new Object[]{ viagemValorClasse.getNavioClasse().getClasse().getNome() + " - Gratuidade", "", "", "" });
                }
            }
        }
    }

    private void carregarComboViagem() {
        ViagemDAO viagemDAO = new ViagemDAO();
        List<Viagem> viagemList = viagemDAO.listar(tfData.getText());

        cbViagens.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if(value != null) {
                    Viagem viagem = (Viagem) value;
                    setText(String.format("%tH:%tM para %s (%s)", viagem.getHoraSaida(), viagem.getHoraSaida(), viagem.getDestino().getNome(), viagem.getNavio().getNome()));
                }

                return this;
            }
        });

        cbViagens.removeAllItems();

        for (Viagem viagem : viagemList) {
            cbViagens.addItem(viagem);
        }
    }

    private void createUIComponents() {
        try {
            SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
            final Calendar hoje = Calendar.getInstance();

            MaskFormatter formatter = new MaskFormatter("##/##/####");
            tfData = new JFormattedTextField(formatter);
            tfData.setColumns(10);
            tfData.setText(formatData.format(hoje.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static class VoltarActionListener implements ActionListener {

        private MapaArrecadacaoForm mapaArrecadacaoForm;

        private VoltarActionListener(MapaArrecadacaoForm mapaArrecadacaoForm) {
            this.mapaArrecadacaoForm = mapaArrecadacaoForm;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            mapaArrecadacaoForm.main.abrir(MenuPrincipal.class.getCanonicalName());
        }
    }

    private static class AlterarDataActionListener implements ActionListener {

        private MapaArrecadacaoForm mapaArrecadacaoForm;

        public AlterarDataActionListener(MapaArrecadacaoForm mapaArrecadacaoForm) {
            this.mapaArrecadacaoForm = mapaArrecadacaoForm;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            mapaArrecadacaoForm.carregar();
        }
    }

    private static class AlteraComboActionListener implements ActionListener {

        private MapaArrecadacaoForm mapaArrecadacaoForm;

        public AlteraComboActionListener(MapaArrecadacaoForm mapaArrecadacaoForm) {
            this.mapaArrecadacaoForm = mapaArrecadacaoForm;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            mapaArrecadacaoForm.carregarTabela();
        }
    }
}
