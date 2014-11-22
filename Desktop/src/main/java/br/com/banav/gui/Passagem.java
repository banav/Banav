package br.com.banav.gui;

import br.com.banav.dao.ClasseDAO;
import br.com.banav.dao.PassagemDAO;
import br.com.banav.dao.ViagemValorClasseDAO;
import br.com.banav.exception.ImpressoraError;
import br.com.banav.gui.component.JButtonData;
import br.com.banav.model.Classe;
import br.com.banav.model.UsuarioLocal;
import br.com.banav.model.Viagem;
import br.com.banav.model.ViagemValorClasse;
import br.com.banav.util.Session;
import br.com.banav.util.Util;
import com.lowagie.text.pdf.BarcodeEAN;
import nfiscal.BematechNFiscal;
import nfiscal.Ticket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * Created by gilson on 4/1/14.
 */
public class Passagem extends JPanel {
    static {
        System.setProperty("jna.library.path", "C:\\Windows\\SysWOW64");
    }
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
    private JCheckBox impressaoAntecipadaCheckBox;

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
        btVoltar.addActionListener(new VoltarActionListener(this));
        tablePassagem.addKeyListener(new DeletarKeyAdapter(this));
        tablePassagem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void carregar(Viagem viagem) {
        this.viagem = viagem;

        painelInteira();
        painelMeia();
        painelGratuidade();

        for( ActionListener al : butFinalizar.getActionListeners() ) {
            butFinalizar.removeActionListener(al);
        }

        butFinalizar.setFont(new Font("Arial", Font.BOLD, 28));
        butFinalizar.addActionListener(new FinalizarActionListener(this, viagem, main));

        labelQuantidade.setFont(new Font("Arial", Font.BOLD, 28));
        labelTotal.setFont(new Font("Arial", Font.BOLD, 28));

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        tablePassagem.setModel(tableModel);
    }

    private void painelMeia() {
        meiaPanel.removeAll();

        ClasseDAO classeDAO = new ClasseDAO();
        final List<Classe> classesMeiaPassagem = classeDAO.getClassesMeiaPassagem(viagem);

        for (Classe classe : classesMeiaPassagem) {
            JButtonData butClasse = new JButtonData(classe.getNome());
            butClasse.setBackground(Color.YELLOW);
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
            butClasse.setBackground(Color.GREEN);
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
            butClasse.setBackground(Color.RED);
            butClasse.setData(classe);
            butClasse.setFont(new Font("Arial", Font.BOLD, 20));
            butClasse.addActionListener(new AddPassagemActionListener(this));

            gratuidadePanel.add(butClasse);
        }
    }

    private void iniciarTabelaPassagens() {
        columns.add("Quantidade");
        columns.add("Classe");
        columns.add("Tipo de Passagem");
        columns.add("Valor");
        columns.add("");

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        tablePassagem.setModel(tableModel);
        tablePassagem.setFont(new Font("Arial", Font.BOLD, 20));
        tablePassagem.setRowHeight(40);

        //tablePassagem.removeColumn(tablePassagem.getColumnModel().getColumn(3));
        //tablePassagem.getColumnModel().getColumn(3).setMinWidth(0);
        //tablePassagem.getColumnModel().getColumn(3).setMaxWidth(0);
        //tablePassagem.getColumnModel().getColumn(3).setWidth(0);

        //tablePassagem.revalidate();
        //tablePassagem.repaint();
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

        String quantidadeStr = JOptionPane.showInputDialog(this, "Informe a quantidade", 1);
        if (quantidadeStr == null || quantidadeStr.isEmpty())
            return;
        int quantidade = new Integer(quantidadeStr);
        double valorTotal = Double.parseDouble(valor) * quantidade;

        DefaultTableModel model = (DefaultTableModel) tablePassagem.getModel();
        model.addRow(new Object[]{quantidade, viagemValorClasse.getNavioClasse().getClasse().getNome(), descricaoTipo, valorTotal, viagemValorClasse});

        calculaValores(model);
    }

    private void calculaValores(DefaultTableModel model) {
        Vector dataVector = model.getDataVector();
        ArrayList _data = new ArrayList(dataVector);

        double total = 0;
        int quantidade = 0;
        for (Object objRow : _data) {
            Vector row = (Vector) objRow;
            quantidade += Integer.parseInt(row.get(0).toString());
            total += Double.parseDouble(row.get(3).toString().replace(",","."));
        }

        labelQuantidade.setText("Quantidade: " + quantidade + "     ");
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
            Boolean prod = true;
            try {
                String computerName = InetAddress.getLocalHost().getHostName();
                if(computerName != null && computerName.equalsIgnoreCase("gilson-note")) {
                   prod = false;
                }
            } catch (UnknownHostException e) {

            }

            if(prod) {
                try {
                    BematechNFiscal cupom = BematechNFiscal.Instance;

                    int iRetorno;
                    iRetorno = cupom.ConfiguraModeloImpressora(7);
                    iRetorno = cupom.IniciaPorta("USB");

                    iRetorno = cupom.Le_Status();

                    switch (iRetorno) {
                        case BematechNFiscal.ERRO_COMUNICACAO:
                            throw new ImpressoraError("Erro de Comunicação com a Impressora!");
                        case BematechNFiscal.SEM_PAPEL:
                            throw new ImpressoraError("Impressora sem Papel!");
                        case BematechNFiscal.TAMPA_ABERTA:
                            throw new ImpressoraError("Tampa da Impressora Aberta!");
                        case BematechNFiscal.POUCO_PAPEL:
                            throw new ImpressoraError("Impressora com pouco papel! Favor trocar a bobina.");
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(main, e.getMessage());
                    return;
                }
            }

            PassagemDAO passagemDAO = new PassagemDAO();

            BarcodeEAN barcodeEAN = new BarcodeEAN();
            barcodeEAN.setCodeType(BarcodeEAN.EAN8);

            DefaultTableModel model = (DefaultTableModel) passagem.tablePassagem.getModel();

            Vector dataVector = model.getDataVector();
            ArrayList _data = new ArrayList(dataVector);

            SimpleDateFormat dataPadrao = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat horaPadrao = new SimpleDateFormat("HH:mm");

            double total = 0;
            for (Object objRow : _data) {
                Vector row = (Vector) objRow;

                int quantidade = Integer.parseInt(row.get(0).toString());
                for (int i = 0; i < quantidade; i++) {
                    double valor = Double.parseDouble(row.get(3).toString().replace(",","."));
                    total += valor;

                    br.com.banav.model.Passagem _passagem = new br.com.banav.model.Passagem();
                    _passagem.setGratuidade(valor == 0);

                    if(valor != 0) {
                        _passagem.setValor(valor / quantidade);
                    } else {
                        _passagem.setValor(valor);
                    }

                    ViagemValorClasse vvc = (ViagemValorClasse) row.get(4);
                    _passagem.setViagemValorClasse(vvc);
                    _passagem.setUsuario((UsuarioLocal) Session.get("usuario"));
                    _passagem.setEnviado(false);

                    if(passagem.impressaoAntecipadaCheckBox.isSelected()) {
                        _passagem.setDataVenda(vvc.getViagem().getHoraSaida());
                    }
                    else {
                        _passagem.setDataVenda(new Date());
                    }

                    Integer nextval = passagemDAO.nextval(viagem.getId());
                    _passagem.setCodigoBarras(Util.gerarCodigoDeBarras(viagem, nextval, _passagem.getUsuario()));

                    passagemDAO.salvar(_passagem);

                    if(prod) {
                        try {
                            Ticket.imprimir(
                                viagem.getOrigem().getNome(),
                                viagem.getDestino().getNome(),
                                dataPadrao.format(viagem.getHoraSaida()),
                                horaPadrao.format(viagem.getHoraSaida()),
                                row.get(1).toString() + (valor == 0 ? " / Gratuidade" : ""),
                                String.format("%.2f", valor),
                                _passagem.getCodigoBarras(),
                                null
                            );
                        } catch(Exception e) {
                            passagemDAO.excluir(br.com.banav.model.Passagem.class, _passagem.getId());
                        }
                    } else {
                        System.out.print(row.get(1).toString() + (valor == 0 ? " / Gratuidade" : ""));
                        System.out.println(" " + _passagem.getCodigoBarras());
                    }
                }
            }

            TableModel tableModel = new DefaultTableModel(passagem.values.toArray(new Object[][] {}), passagem.columns.toArray());
            passagem.tablePassagem.setModel(tableModel);
            passagem.labelTotal.setText("Finalizado com sucesso.");
            passagem.labelQuantidade.setText("           ");
        }
    }

    private static class VoltarActionListener implements ActionListener {

        private Passagem passagem;

        private VoltarActionListener(Passagem passagem) {
            this.passagem = passagem;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            passagem.main.abrirDestinos();
        }
    }

    private static class DeletarKeyAdapter extends KeyAdapter {

        private Passagem passagem;

        private DeletarKeyAdapter(Passagem passagem) {
            this.passagem = passagem;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_DELETE) {
                if(passagem.tablePassagem.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) passagem.tablePassagem.getModel();
                    int[] rows = passagem.tablePassagem.getSelectedRows();
                    for(int i=0;i<rows.length;i++){
                        model.removeRow(rows[i]-i);
                    }

                    passagem.calculaValores(model);
                }
            }
        }
    }
}
