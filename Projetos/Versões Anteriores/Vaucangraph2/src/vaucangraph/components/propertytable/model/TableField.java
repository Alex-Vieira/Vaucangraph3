package vaucangraph.components.propertytable.model;

/**
 * Classe que contém informações de um campo da tabela de propriedades
 * @author Kleber Kruger
 */
public class TableField {

    private int row;
    private int column;
    private Object value;

    public TableField() {
        this(0, 0, "");
    }

    public TableField(int row, int column, Object value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }

    /**
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * @param row the row to set
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * @param column the column to set
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }
}
