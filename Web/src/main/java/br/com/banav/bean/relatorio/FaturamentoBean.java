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

    private Double totalMensal;

    private LineChartModel faturamentoModel;

    private List<DataValorDTO> dataValorDTOs;

    @EJB private FaturamentoSrv faturamentoSrv;

    @URLAction(mappingId = "faturamento", onPostback = false)
    public void init() {
        Calendar hoje = Calendar.getInstance();

        ano = hoje.get(Calendar.YEAR);
        mes = hoje.get(Calendar.MONTH);

        faturamentoModel = new LineChartModel();
    }

    public void gerarRelatorio() {
        createModel();
    }

    private void createModel() {
        totalMensal = 0D;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Calendar dataInicial = Calendar.getInstance();
        dataInicial.set(ano, mes, 1);

        Calendar dataFinal = Calendar.getInstance();
        dataFinal.setTime(dataInicial.getTime());
        dataFinal.set(Calendar.DAY_OF_MONTH, dataFinal.getActualMaximum(Calendar.DAY_OF_MONTH));

        faturamentoModel = new LineChartModel();
        //faturamentoModel.setLegendPosition("e");
        faturamentoModel.setShowPointLabels(true);

        LineChartSeries faturamentoDiario = new LineChartSeries();
        faturamentoDiario.setLabel("Faturamento Diário");

        dataValorDTOs = faturamentoSrv.listarPor(dataInicial.getTime(), dataFinal.getTime());
        for (DataValorDTO dataValorDTO : dataValorDTOs) {
            faturamentoDiario.set(simpleDateFormat.format(dataValorDTO.getData()), dataValorDTO.getValor());
            totalMensal += dataValorDTO.getValor();
        }

        faturamentoModel.addSeries(faturamentoDiario);

        faturamentoModel.setTitle("Faturamento Mensal");
        faturamentoModel.setZoom(true);
        faturamentoModel.getAxis(AxisType.Y).setLabel("Valores em R$");

        DateAxis axis = new DateAxis("Dias");
        axis.setTickAngle(-50);
        axis.setMin(simpleDateFormat.format(dataInicial.getTime()));
        axis.setMax(simpleDateFormat.format(dataFinal.getTime()));
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

    public Double getTotalMensal() {
        return totalMensal;
    }

    public void setTotalMensal(Double totalMensal) {
        this.totalMensal = totalMensal;
    }

    public LineChartModel getFaturamentoModel() {
        return faturamentoModel;
    }

    public void setFaturamentoModel(LineChartModel faturamentoModel) {
        this.faturamentoModel = faturamentoModel;
    }

    public List<DataValorDTO> getDataValorDTOs() {
        return dataValorDTOs;
    }

    public void setDataValorDTOs(List<DataValorDTO> dataValorDTOs) {
        this.dataValorDTOs = dataValorDTOs;
    }
}
