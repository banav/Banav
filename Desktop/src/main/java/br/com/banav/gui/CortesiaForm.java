package br.com.banav.gui;

import br.com.banav.dao.*;
import br.com.banav.dao.common.DAO;
import br.com.banav.exception.ImpressoraError;
import br.com.banav.model.*;
import br.com.banav.model.Passagem;
import br.com.banav.model.UsuarioLocal;
import br.com.banav.util.Session;
import br.com.banav.util.Util;
import nfiscal.BematechNFiscal;
import nfiscal.Ticket;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created by gilson on 5/2/14.
 */
public class CortesiaForm extends JPanel {
    private JPanel mainContent;
    private JButton btVoltar;
    private JTable tableCortesia;
    private JButton btEmitirPassagem;
    private JFormattedTextField tfData;

    private ArrayList<String[]> values = new ArrayList<String[]>();
    private ArrayList<String> columns = new ArrayList<String>();

    private Main main;

    public CortesiaForm(Main main) {
        this.main = main;

        setLayout(new BorderLayout());
        add(mainContent, BorderLayout.CENTER);
        columns.add("Código");
        columns.add("Nome");
        columns.add("RG");
        columns.add("CPF");
        columns.add("Data/Hora Viagem");

        tableCortesia.setFont(new Font("Arial", Font.BOLD, 20));
        tableCortesia.setRowHeight(40);
        tableCortesia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        btVoltar.addActionListener(new VoltarActionListener(this));
        btEmitirPassagem.addActionListener(new EmitirPassagemActionListener(this));
        tfData.addActionListener(new AlterarDataActionListener(this));
    }

    public void carregar() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        CortesiaDAO cortesiaDAO = new CortesiaDAO();
        final List<Cortesia> cortesias = cortesiaDAO.listar(tfData.getText());

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        tableCortesia.setModel(tableModel);

        DefaultTableModel model = (DefaultTableModel) tableCortesia.getModel();
        for (Cortesia cortesia : cortesias) {
            model.addRow(new Object[]{cortesia.getId(), cortesia.getNome(), cortesia.getRg(), cortesia.getCpf(), dateFormat.format(cortesia.getViagem().getHoraSaida())});
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

    private static class EmitirPassagemActionListener implements ActionListener {

        private CortesiaForm cortesiaForm;

        private Set<String> codigoBarras;

        private Log log = LogFactory.getLog(this.getClass());

        private EmitirPassagemActionListener(CortesiaForm cortesiaForm) {
            this.cortesiaForm = cortesiaForm;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                codigoBarras  = new HashSet<String>();
                BematechNFiscal cupom = BematechNFiscal.Instance;

                int iRetorno;
                iRetorno = cupom.ConfiguraModeloImpressora(7);
                iRetorno = cupom.IniciaPorta("USB");

                iRetorno = cupom.Le_Status();

                if(iRetorno == BematechNFiscal.ERRO_COMUNICACAO)
                    throw new ImpressoraError("Erro de Comunicação com a Impressora!");
                else if(iRetorno == BematechNFiscal.SEM_PAPEL)
                    throw new ImpressoraError("Impressora sem Papel!");
                else if(iRetorno == BematechNFiscal.TAMPA_ABERTA)
                    throw new ImpressoraError("Tampa da Impressora Aberta!");
            } catch(Exception e) {
                JOptionPane.showMessageDialog(cortesiaForm, e.getMessage());
                return;
            }

            if(cortesiaForm.tableCortesia.getSelectedRow() != -1) {
                CortesiaDAO cortesiaDAO = new CortesiaDAO();
                br.com.banav.model.Passagem passagem = null;
                try {

                    cortesiaDAO.setAutoCommit(false);
                    cortesiaDAO.getEM().getTransaction().begin();

                    final Object valueAt = cortesiaForm.tableCortesia.getModel().getValueAt(cortesiaForm.tableCortesia.getSelectedRow(), 0);


                    Cortesia cortesia = cortesiaDAO.getUm(Cortesia.class, new Long(valueAt.toString()));
                    Viagem viagem = cortesia.getViagem();
                    NavioClasseDAO navioClasseDAO = new NavioClasseDAO();
                    final List<NavioClasse> navioClasses = navioClasseDAO.listarPor(viagem.getNavio());

                    Object[] possibleValues = new Object[navioClasses.size()];
                    int i = 0;
                    for (NavioClasse navioClasse : navioClasses) {
                        possibleValues[i] = navioClasse.getClasse();
                        ++i;
                    }

                    Object selectedValue = JOptionPane.showInputDialog(null, "Selecione uma classe:", "Emitir Passagem", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);

                    if(selectedValue != null) {
                        PassagemDAO passagemDAO = new PassagemDAO(cortesiaDAO.getEM());
                        passagemDAO.setAutoCommit(false);

                        Classe classeSelecionada = (Classe) selectedValue;

                        ViagemValorClasseDAO viagemValorClasseDAO = new ViagemValorClasseDAO();
                        ViagemValorClasse viagemValorClasse = viagemValorClasseDAO.getPor(viagem, classeSelecionada);

                        passagem = new Passagem();
                        passagem.setViagemValorClasse(viagemValorClasse);
                        passagem.setCodigoBarras(null);
                        passagem.setValor(0D);
                        passagem.setGratuidade(true);
                        passagem.setEnviado(false);

                        boolean naoRepetido;
                        do{
                            naoRepetido = false;
                            Integer nextval = passagemDAO.nextval(viagem.getId());
                            String codigoBarra = Util.gerarCodigoDeBarras(viagem, nextval, (UsuarioLocal) Session.get("usuario"));
                            naoRepetido = codigoBarras.add(codigoBarra);
                            if(naoRepetido)
                                passagem.setCodigoBarras(codigoBarra);
                        }while(!naoRepetido);

                        passagem = passagemDAO.salvarFlush(passagem);

//                        String number = String.format("%07d", passagem.getId());
//                        passagem.setCodigoBarras(number);
//
//                        passagemDAO.atualizar(passagem);

                        cortesia.setPassagem(passagem);
                        cortesiaDAO.atualizar(cortesia);

                        SimpleDateFormat dataPadrao = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat horaPadrao = new SimpleDateFormat("hh:mm");



                        Ticket.imprimir(
                            viagem.getOrigem().getNome(),
                            viagem.getDestino().getNome(),
                            dataPadrao.format(viagem.getHoraSaida()),
                            horaPadrao.format(viagem.getHoraSaida()),
                            "Cortesia",
                            String.format("%.2f", 0F),
                            passagem.getCodigoBarras(),
                            cortesia.getNome()
                        );

                        cortesiaForm.carregar();

                        cortesiaDAO.getEM().getTransaction().commit();

                    }
                } catch (Exception ex) {
                    log.error(ex.getMessage(), ex);
                    log.info("Tentando remover a passagem de codigo de barras "
                            + passagem == null ? "passagem nula" : passagem.getCodigoBarras());
                    try{
                        cortesiaDAO.getEM().getTransaction().rollback();
                    }
                    catch (Exception e){
                        log.error("Erro ao dar rollback na passagem: " + passagem == null ? "passagem nula" : passagem.getCodigoBarras());
                        log.error(e.getMessage(), e);
                    }
                        ex.printStackTrace();
                } finally {
                    DAO.setAutoCommit(true);
                }

            } else {
                JOptionPane.showMessageDialog(cortesiaForm.main, "Selecione uma cortesia");
            }
        }
    }

    private static class VoltarActionListener implements ActionListener {

        private CortesiaForm cortesiaForm;

        private VoltarActionListener(CortesiaForm cortesiaForm) {
            this.cortesiaForm = cortesiaForm;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            cortesiaForm.main.abrir(MenuPrincipal.class.getCanonicalName());
        }
    }

    private static class AlterarDataActionListener implements ActionListener {

        private final CortesiaForm cortesiaForm;

        public AlterarDataActionListener(CortesiaForm cortesiaForm) {
            this.cortesiaForm = cortesiaForm;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            cortesiaForm.carregar();
        }
    }
}
