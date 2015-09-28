package br.com.banav.gui;

import br.com.banav.dao.MapaArrecadacaoDAO;
import br.com.banav.dao.MapaViagemDAO;
import br.com.banav.dao.ViagemDAO;
import br.com.banav.dao.ViagemValorClasseDAO;
import br.com.banav.model.*;
import br.com.banav.util.Session;

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
import java.util.Vector;

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
        columns.add("");

        tableMapa.setFont(new Font("Arial", Font.BOLD, 20));
        tableMapa.setRowHeight(40);
        tableMapa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        btVoltar.addActionListener(new VoltarActionListener(this));
        tfData.addActionListener(new AlterarDataActionListener(this));
        cbViagens.addActionListener(new AlteraComboActionListener(this));
        btEnviarMapa.addActionListener(new EnviarMapaActionListener(this));
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
                model.addRow(new Object[]{ viagemValorClasse.getNavioClasse().getClasse().getNome() + " - Inteira", "", "", "", viagemValorClasse });

                if(viagemValorClasse.getValorMeia() != null && viagemValorClasse.getValorMeia() > 0) {
                    model.addRow(new Object[]{ viagemValorClasse.getNavioClasse().getClasse().getNome() + " - Meia", "", "", "", viagemValorClasse });
                }

                if(viagemValorClasse.getAceitaGratuidade()) {
                    model.addRow(new Object[]{ viagemValorClasse.getNavioClasse().getClasse().getNome() + " - Gratuidade", "", "", "", viagemValorClasse });
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

    private void salvar() {
        DefaultTableModel model = (DefaultTableModel) tableMapa.getModel();
        Vector dataVector = model.getDataVector();
        ArrayList _data = new ArrayList(dataVector);
        UsuarioLocal usuarioLocal = (UsuarioLocal) Session.get("usuario");
        Viagem viagemSelecionada = (Viagem) cbViagens.getSelectedItem();
        List<MapaViagem> mapasViagem = new ArrayList<MapaViagem>();

        double total = 0;
        for (Object objRow : _data) {
            Vector row = (Vector) objRow;

            double valor = Double.parseDouble(row.get(3).toString().replace(",","."));
            total += valor;

            MapaViagem mapaViagem = new MapaViagem();
            mapaViagem.setNumeracaoCupom(row.get(1).toString());
            mapaViagem.setQuantidade(Integer.parseInt(row.get(2).toString()));
            mapaViagem.setViagemValorClasse((ViagemValorClasse) row.get(4));
            mapaViagem.setValor(valor);

            mapasViagem.add(mapaViagem);
        }

        MapaArrecadacao mapaArrecadacao = new MapaArrecadacao();
        mapaArrecadacao.setEnviado(false);
        mapaArrecadacao.setUsuario(usuarioLocal);
        mapaArrecadacao.setViagem(viagemSelecionada);
        mapaArrecadacao.setValor(total);

        MapaArrecadacaoDAO mapaArrecadacaoDAO = new MapaArrecadacaoDAO();
        mapaArrecadacaoDAO.salvar(mapaArrecadacao);

        MapaViagemDAO mapaViagemDAO = new MapaViagemDAO();
        for (MapaViagem mapaViagem : mapasViagem) {
            mapaViagem.setMapaArrecadacao(mapaArrecadacao);
            mapaViagemDAO.salvar(mapaViagem);
        }

        JOptionPane.showMessageDialog(main, "Mapa enviado com sucesso.");
        carregarTabela();
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

    private static class EnviarMapaActionListener implements ActionListener {

        private MapaArrecadacaoForm mapaArrecadacaoForm;

        public EnviarMapaActionListener(MapaArrecadacaoForm mapaArrecadacaoForm) {
            this.mapaArrecadacaoForm = mapaArrecadacaoForm;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            mapaArrecadacaoForm.salvar();
        }
    }
}
