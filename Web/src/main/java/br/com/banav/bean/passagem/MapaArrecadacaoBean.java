package br.com.banav.bean.passagem;

import br.com.banav.bean.common.PaginaBean;
import br.com.banav.dao.PassagemDAO;
import br.com.banav.model.Usuario;
import br.com.banav.service.PassagemSrv;
import br.com.banav.service.UsuarioSrv;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gustavocosta on 01/02/14.
 */

@ManagedBean
@ViewScoped
@URLMappings(mappings = {
    @URLMapping(id = "mapaArrecadacao", pattern = "/passagem/mapa/arrecadacao", viewId = "/pages/passagem/mapa_arrecadacao.jsf", parentId = "paginaRestrita")
})
public class MapaArrecadacaoBean extends PaginaBean {

    @EJB
    private PassagemSrv passagemSrv;

    @EJB
    private UsuarioSrv usuarioSrv;

    @Inject private PassagemDAO passagemDAO;

    private Date dataInicio = new Date();

    private List<Usuario> vendedores;

    private Usuario vendedor;

    @URLAction(mappingId = "mapaArrecadacao", onPostback = false)
    public void init() {
        vendedores = usuarioSrv.listarVendedores();
    }

    public StreamedContent gerarRelatorio() throws FileNotFoundException, JRException, SQLException, Exception {

        SimpleDateFormat dataSimples = new SimpleDateFormat("dd/MM/yyyy");

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("dataInicio", dataSimples.format(dataInicio));
        parametros.put("idUsuario", vendedor.getId());

        InputStream relatorio = null;
        ByteArrayOutputStream relat = new ByteArrayOutputStream();
        String arquivo = getRequest().getServletContext().getRealPath("/WEB-INF/jasper/mapa-arrecadacao.jasper");

        Session session = passagemDAO.getEm().unwrap(Session.class);
        SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
        ConnectionProvider cp = sfi.getConnectionProvider();

        Connection connection = null;

        try{
            connection = cp.getConnection();

            JasperPrint print = JasperFillManager.fillReport(new FileInputStream(new File(arquivo)), parametros, connection);
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, relat);
            exporter.exportReport();
            relatorio = new ByteArrayInputStream(relat.toByteArray());

            return new DefaultStreamedContent(relatorio, "application/pdf", "Relatorio_Arrecadacao.pdf");
        }
        catch (FileNotFoundException e){
            throw e;
        }
        catch (JRException e){
            throw e;
        }
        catch (SQLException e){
            throw e;
        }
        catch (Exception e){
            throw e;
        }
        finally {
            if (connection != null){
                try{
                    if(!connection.isClosed())
                        connection.close();
                }
                catch (SQLException e){
                    throw e;
                }
            }
        }
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public List<Usuario> getVendedores() {
        return vendedores;
    }

    public void setVendedores(List<Usuario> vendedores) {
        this.vendedores = vendedores;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }
}
