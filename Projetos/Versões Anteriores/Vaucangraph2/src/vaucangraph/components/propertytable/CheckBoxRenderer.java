package vaucangraph.components.propertytable;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Renderizador de check box da tabela de propriedades vaucangraph *
 * @author Kleber Kruger
 */
public class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {

    public CheckBoxRenderer() {
        // Alinha o checkbox ao centro
        setHorizontalAlignment(JLabel.CENTER);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean selected, boolean focused, int row, int column) {

        if (selected) {
            // Define os estilos de background e foreground
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());

        } else {
            // Define os estilos de background e foreground
            setBackground(table.getBackground());
            setForeground(table.getForeground());

        }

        setSelected((value != null && ((Boolean) value).booleanValue()));
        return this;
    }
}
