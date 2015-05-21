package br.com.banav.bean.relatorio;

import br.com.banav.bean.common.PaginaBean;
import br.com.banav.model.dto.DataValorDTO;
import br.com.banav.service.FaturamentoSrv;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by gilson on 18/05/15.
 */

@ManagedBean
@RequestScoped
@URLMapping(id = "faturamento", pattern = "/faturamento/", viewId = "/pages/relatorio/faturamento.jsf", parentId = "paginaRestrita")
public class FaturamentoBean extends PaginaBean {

    private Integer ano;

    private Integer mes;

    private LineChartModel faturamentoModel;

    @EJB private FaturamentoSrv faturamentoSrv;

    @URLAction(mappingId = "faturamento", onPostback = false)
    public void init() {
        Calendar hoje = Calendar.getInstance();

        ano = hoje.get(Calendar.YEAR);
        mes = hoje.get(Calendar.MONTH);

        //createModel();
        faturamentoModel = new LineChartModel();
    }

    public void gerarRelatorio() {
        createModel();
    }

    private void createModel() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        faturamentoModel = new LineChartModel();
        //faturamentoModel.setLegendPosition("e");
        faturamentoModel.setShowPointLabels(true);

        LineChartSeries faturamentoDiario = new LineChartSeries();
        faturamentoDiario.setLabel("Faturamento Diário");

        List<DataValorDTO> dataValorDTOs = faturamentoSrv.listarPor(mes, ano);
        for (DataValorDTO dataValorDTO : dataValorDTOs) {
            faturamentoDiario.set(simpleDateFormat.format(dataValorDTO.getData()), dataValorDTO.getValor());
        }

        faturamentoModel.addSeries(faturamentoDiario);

        faturamentoModel.setTitle("Faturamento Mensal");
        faturamentoModel.setZoom(true);
        faturamentoModel.getAxis(AxisType.Y).setLabel("Valores em R$");

        DateAxis axis = new DateAxis("Dias");
        axis.setTickAngle(-50);
        axis.setMin("2014-08-01");
        axis.setTickFormat("%#d/%m/%y");
        axis.setTickInterval("1 day");

        faturamentoModel.getAxes().put(AxisType.X, axis);
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public LineChartModel getFaturamentoModel() {
        return faturamentoModel;
    }

    public void setFaturamentoModel(LineChartModel faturamentoModel) {
        this.faturamentoModel = faturamentoModel;
    }
}
