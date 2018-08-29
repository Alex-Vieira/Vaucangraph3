package vaucangraph.components.propertytable;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import org.jgraph.graph.AttributeMap;

/**
 *
 * @author Kleber Kruger
 */
public class EachRowRenderer implements TableCellRenderer {

    protected AttributeMap renderers;
    protected TableCellRenderer renderer, defaultRenderer;

    public EachRowRenderer() {
        renderers = new AttributeMap();
        defaultRenderer = new DefaultTableCellRenderer();
    }

    public void add(int row, TableCellRenderer renderer) {
        renderers.put(new Integer(row), renderer);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean selected, boolean focused, int row, int column) {

        renderer = (TableCellRenderer) renderers.get(new Integer(row));

        if (renderer == null) {
            renderer = defaultRenderer;
        }
        return renderer.getTableCellRendererComponent(table, value, selected,
                focused, row, column);
    }
}
