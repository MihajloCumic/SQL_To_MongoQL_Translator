package view.model;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class TableModel extends DefaultTableModel {
    private List<Map<String, String>> rows;

    private void updateModel(){
        int columnCount = rows.get(0).keySet().size();

        Vector columnVector = DefaultTableModel.convertToVector(rows.get(0).keySet().toArray());
        Vector dataVector = new Vector(columnCount);

        for(int i = 0; i < rows.size(); i++){
            dataVector.add(DefaultTableModel.convertToVector(rows.get(i).values().toArray()));
        }

        setDataVector(dataVector, columnVector);
    }

    public void setRows(List<Map<String, String>> rows) {
        this.rows = rows;
        updateModel();
    }
}
