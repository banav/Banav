package br.com.banav.gui;

import br.com.banav.dao.ClasseDAO;
import br.com.banav.dao.PassagemDAO;
import br.com.banav.dao.PassagemHistoricoDAO;
import br.com.banav.dao.ViagemValorClasseDAO;
import br.com.banav.gui.component.JButtonData;
import br.com.banav.model.*;
import com.lowagie.text.pdf.BarcodeEAN;
import net.sf.jasperreports.components.barcode4j.EAN8Component;
import nfiscal.Ticket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

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

    private Viagem viagem;

    private ArrayList<String[]> values = new ArrayList<String[]>();

    private ArrayList<String> columns = new ArrayList<String>();

    public Passagem(Main main) {
        this.main = main;

        setLayout(new BorderLayout());
        add(mainContent, BorderLayout.CENTER);

        iniciarTabelaPassagens();
    }

    public void carregar(Viagem viagem) {
        this.viagem = viagem;

        painelInteira();
        painelMeia();
        painelGratuidade();

        butFinalizar.setFont(new Font("Arial", Font.BOLD, 28));
        butFinalizar.addActionListener(new FinalizarActionListener(this, viagem, main));

        labelQuantidade.setFont(new Font("Arial", Font.BOLD, 28));
        labelTotal.setFont(new Font("Arial", Font.BOLD, 28));
    }

    private void painelMeia() {
        meiaPanel.removeAll();

        ClasseDAO classeDAO = new ClasseDAO();
        final List<Classe> classesMeiaPassagem = classeDAO.getClassesMeiaPassagem(viagem);

        for (Classe classe : classesMeiaPassagem) {
            JButtonData butClasse = new JButtonData(classe.getNome());
            butClasse.setData(classe);
            butClasse.setFont(new Font("Arial", Font.BOLD, 20));
            butClasse.addActionListener(new AddPassagemActionListener(this));

            meiaPanel.add(butClasse);
        }
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
        final List<Classe> classesMeiaPassagem = classeDAO.getClassesGratuidade(viagem);

        for (Classe classe : classesMeiaPassagem) {
            JButtonData butClasse = new JButtonData(classe.getNome());
            butClasse.setData(classe);
            butClasse.setFont(new Font("Arial", Font.BOLD, 20));
            butClasse.addActionListener(new AddPassagemActionListener(this));

            gratuidadePanel.add(butClasse);
        }
    }

    private void iniciarTabelaPassagens() {
        columns.add("Classe");
        columns.add("Tipo de Passagem");
        columns.add("Valor");

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        tablePassagem.setModel(tableModel);
        tablePassagem.setFont(new Font("Arial", Font.BOLD, 20));
        tablePassagem.setRowHeight(40);
    }

    private void addPassagem(ViagemValorClasse viagemValorClasse, Integer tipo) {
        String descricaoTipo = "";
        String valor = "";

        if(tipo.equals(MEIA)) {
            descricaoTipo = "Meia Passagem";
            valor = viagemValorClasse.getValorMeia().toString();
        } else if(tipo.equals(INTEIRA)) {
            descricaoTipo = "Inteira";
            valor = viagemValorClasse.getValor().toString();
        } else if(tipo.equals(GRATUIDADE)) {
            descricaoTipo = "Gratuidade";
            valor = "0";
        }

        DefaultTableModel model = (DefaultTableModel) tablePassagem.getModel();
        model.addRow(new Object[]{viagemValorClasse.getNavioClasse().getClasse().getNome(), descricaoTipo, valor});

        Vector dataVector = model.getDataVector();
        ArrayList _data = new ArrayList(dataVector);

        labelQuantidade.setText("Quantidade: " + _data.size() + "     ");

        double total = 0;
        for (Object objRow : _data) {
            Vector row = (Vector) objRow;
            total += Double.parseDouble(row.get(2).toString().replace(",","."));
        }

        labelTotal.setText("Total: R$ " + String.format("%.2f", total) + "     ");
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

            ViagemValorClasseDAO viagemValorClasseDAO = new ViagemValorClasseDAO();
            ViagemValorClasse _viagemValorClasse = viagemValorClasseDAO.getPor(passagem.viagem, classe);

            Integer tipo = null;
            if(butClasse.getParent().equals(passagem.meiaPanel)) {
                tipo = Passagem.MEIA;
            } else if(butClasse.getParent().equals(passagem.inteiraPanel)) {
                tipo = Passagem.INTEIRA;
            } else if(butClasse.getParent().equals(passagem.gratuidadePanel)) {
                tipo = Passagem.GRATUIDADE;
            }

            passagem.addPassagem(_viagemValorClasse, tipo);
        }
    }

    private static class FinalizarActionListener implements ActionListener {
        private final Passagem passagem;
        private final Viagem viagem;
        private final Main main;

        public FinalizarActionListener(Passagem passagem, Viagem viagem, Main main) {
            this.passagem = passagem;
            this.viagem = viagem;
            this.main = main;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PassagemDAO passagemDAO = new PassagemDAO();
            PassagemHistoricoDAO passagemHistoricoDAO = new PassagemHistoricoDAO();

            BarcodeEAN barcodeEAN = new BarcodeEAN();
            barcodeEAN.setCodeType(BarcodeEAN.EAN8);

            DefaultTableModel model = (DefaultTableModel) passagem.tablePassagem.getModel();

            Vector dataVector = model.getDataVector();
            ArrayList _data = new ArrayList(dataVector);

            SimpleDateFormat dataPadrao = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat horaPadrao = new SimpleDateFormat("hh:mm");

            double total = 0;
            for (Object objRow : _data) {
                Vector row = (Vector) objRow;

                double valor = Double.parseDouble(row.get(2).toString().replace(",","."));
                total += valor;

                br.com.banav.model.Passagem _passagem = new br.com.banav.model.Passagem();
                _passagem.setGratuidade(valor == 0);
                _passagem.setValor(valor);
                _passagem.setCheckin(false);

                passagemDAO.salvar(_passagem);

                String number = String.format("%07d", _passagem.getId());
                _passagem.setCodigoBarras(number);// + barcodeEAN.calculateEANParity(number));

                passagemDAO.atualizar(_passagem);

                PassagemHistorico passagemHistorico = new PassagemHistorico();
                passagemHistorico.setData(new Date());
                passagemHistorico.setPassagemMovimento(PassagemMovimento.MARCADA);
                passagemHistorico.setPassagem(_passagem);

                passagemHistoricoDAO.salvar(passagemHistorico);

                Ticket.imprimir(
                    viagem.getOrigem().getNome(),
                    viagem.getDestino().getNome(),
                    dataPadrao.format(viagem.getHoraSaida()),
                    horaPadrao.format(viagem.getHoraSaida()),
                    row.get(1).toString(),
                    String.format("%.2f", valor),
                    _passagem.getCodigoBarras()
                );
            }

            TableModel tableModel = new DefaultTableModel(passagem.values.toArray(new Object[][] {}), passagem.columns.toArray());
            passagem.tablePassagem.setModel(tableModel);
            passagem.labelTotal.setText("");
            passagem.labelQuantidade.setText("");

            JOptionPane.showMessageDialog(null, "Finalizado com sucesso.");

            main.abrir(MenuPrincipal.class.getCanonicalName());
        }
    }
}
