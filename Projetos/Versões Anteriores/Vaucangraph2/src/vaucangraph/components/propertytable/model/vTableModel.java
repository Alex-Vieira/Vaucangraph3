package vaucangraph.components.propertytable.model;

import javax.swing.table.DefaultTableModel;
import vaucangraph.model.enumeration.ProjectType;

/**
 * Modelo personalizado de tabela Vaucangraph
 * @author Kleber Kruger
 */
public final class vTableModel extends DefaultTableModel {

    public vTableModel(ProjectType type) {
        super();
        setDataVector(getDataVector(type), getColumnIdentifiers());
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (column == TableFields.COLUMN_VALUE) {
            return true;
        }
        return false;
    }

    /**
     * @return as informações iniciais da tabela de propriedades
     */
    private Object[][] getDataVector(ProjectType type) {

        if (type == ProjectType.AUTOMATON) {
            return new Object[][]{
                        {TableFields.PROPERTYNAME_NAME, ""},
                        {TableFields.PROPERTYNAME_SIZE, ""},
                        {TableFields.PROPERTYNAME_INITIAL, false},
                        {TableFields.PROPERTYNAME_FINAL, false},
                        {TableFields.PROPERTYNAME_DIMMED, false},
                        {TableFields.PROPERTYNAME_HIDDEN, false},
                        {TableFields.PROPERTYNAME_VARIABLESIZE, false},
                        {TableFields.PROPERTYNAME_POSX, ""},
                        {TableFields.PROPERTYNAME_POSY, ""}};
        } else {
            return new Object[][]{
                        {TableFields.PROPERTYNAME_NAME, ""},
                        {TableFields.PROPERTYNAME_SIZE, ""},
                        {TableFields.PROPERTYNAME_DIMMED, false},
                        {TableFields.PROPERTYNAME_HIDDEN, false},
                        {TableFields.PROPERTYNAME_VARIABLESIZE, false},
                        {TableFields.PROPERTYNAME_POSX, ""},
                        {TableFields.PROPERTYNAME_POSY, ""}};
        }
    }

    /**
     * @return o nome das colunas da tabela de propriedades
     */
    protected Object[] getColumnIdentifiers() {
        return new Object[]{TableFields.COLUMNNAME_PROPERTY, TableFields.COLUMNNAME_VALOR};
    }
}
