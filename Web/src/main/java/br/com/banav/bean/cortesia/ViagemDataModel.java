package br.com.banav.bean.cortesia;

import br.com.banav.model.Cortesia;
import br.com.banav.model.Viagem;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 * Created by gilson on 4/23/14.
 */
public class ViagemDataModel extends ListDataModel<Viagem> implements SelectableDataModel<Viagem> {

    public ViagemDataModel(List<Viagem> data) {
        super(data);
    }

    @Override
    public Object getRowKey(Viagem viagem) {
        return viagem.getId();
    }

    @Override
    public Viagem getRowData(String rowKey) {
        List<Viagem> viagens = (List<Viagem>) getWrappedData();

        for(Viagem viagem : viagens) {
            if(viagem.getId().equals(new Long(rowKey)))
                return viagem;
        }

        return null;
    }
}