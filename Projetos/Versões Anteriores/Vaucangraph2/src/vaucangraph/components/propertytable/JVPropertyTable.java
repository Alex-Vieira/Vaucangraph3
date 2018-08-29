package vaucangraph.components.propertytable;

import vaucangraph.components.propertytable.model.TableFields;
import java.awt.Color;
import java.awt.event.ItemEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import jgraph.model.vGraphCell;
import jgraph.view.Canvas;
import vaucangraph.components.propertytable.model.vTableModel;
import vaucangraph.model.enumeration.ProjectType;
import vaucansong.bean.State;
import vaucansong.bean.Transition;
import vaucansong.bean.enumeration.StateSize;

/**
 * Tabela de propriedades personalizada Vaucangraph
 * @author Kleber Kruger
 */
public class JVPropertyTable extends JTable {

    /** Canvas */
    protected final Canvas canvas;
    /** Classes que contém o modelo da tabela */
    protected EachRowRenderer rowRenderer;
    protected EachRowEditor rowEditor;

    public JVPropertyTable(Canvas canvas) {
        super();

        this.canvas = canvas;
        this.rowRenderer = new EachRowRenderer();
        this.rowEditor = new EachRowEditor(this);

        initComponents();
    }

    private void initComponents() {

        setModel(new vTableModel(canvas.getProject().getType()));

        addRowRenderers(canvas.getProject().getType());
        addRowEditors(canvas.getProject().getType());

        getColumn(TableFields.COLUMNNAME_VALOR).setCellRenderer(rowRenderer);
        getColumn(TableFields.COLUMNNAME_VALOR).setCellEditor(rowEditor);

        setName("PropertyTable");
        setToolTipText("<html>\n<b>Tabela de propriedades</b><br>\n"
                + "Altere as propriedades da célula selecionada\n</html>");
        setGridColor(new Color(204, 204, 204));
        setRowHeight(20);

    }

    protected void addRowRenderers(ProjectType type) {

        CheckBoxRenderer cbr = new CheckBoxRenderer();

        // Renderiza de acodo com uma tabela de autômato
        if (type == ProjectType.AUTOMATON) {
            rowRenderer.add(TableFields.ROW_INITIAL, cbr);
            rowRenderer.add(TableFields.ROW_FINAL, cbr);
            rowRenderer.add(TableFields.ROW_DIMMED_AUTOMATON, cbr);
            rowRenderer.add(TableFields.ROW_HIDDEN_AUTOMATON, cbr);
            rowRenderer.add(TableFields.ROW_VARIABLESIZE_AUTOMATON, cbr);
        } // Renderiza de acodo com uma tabela de grafo
        else {
            rowRenderer.add(TableFields.ROW_DIMMED_GRAPH, cbr);
            rowRenderer.add(TableFields.ROW_HIDDEN_GRAPH, cbr);
            rowRenderer.add(TableFields.ROW_VARIABLESIZE_GRAPH, cbr);
        }
    }

    protected void addRowEditors(ProjectType type) {

        final JComboBox combo = new JComboBox();
        combo.addItem("Muito pequeno");
        combo.addItem("Pequeno");
        combo.addItem("Médio");
        combo.addItem("Grande");

        // Adiciona listener de troca de estado
        combo.addItemListener(new java.awt.event.ItemListener() {

            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                if (evt.getStateChange() == ItemEvent.SELECTED) {

                    vGraphCell cell = (vGraphCell) canvas.getSelectionCell();

                    if (cell instanceof State) {
                        State state = (State) cell;
                        state.setSize(getStateSize(combo));
                    }
                }

            }
        });

        final JCheckBox check = new JCheckBox();
        check.setHorizontalAlignment(JLabel.CENTER);
        check.addItemListener(new java.awt.event.ItemListener() {

            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                setAttributesInState(getSelectedRow(), check.isSelected());
            }
        });

        DefaultCellEditor comboEditor = new DefaultCellEditor(combo);
        DefaultCellEditor checkEditor = new DefaultCellEditor(check);

        rowEditor = new EachRowEditor(this);
        // Editor para tabela de autômato
        if (type == ProjectType.AUTOMATON) {
            rowEditor.setEditorAt(TableFields.ROW_SIZE, comboEditor);
            rowEditor.setEditorAt(TableFields.ROW_INITIAL, checkEditor);
            rowEditor.setEditorAt(TableFields.ROW_FINAL, checkEditor);
            rowEditor.setEditorAt(TableFields.ROW_DIMMED_AUTOMATON, checkEditor);
            rowEditor.setEditorAt(TableFields.ROW_HIDDEN_AUTOMATON, checkEditor);
            rowEditor.setEditorAt(TableFields.ROW_VARIABLESIZE_AUTOMATON, checkEditor);
        } // Editor para tabela de grafo
        else {
            rowEditor.setEditorAt(TableFields.ROW_SIZE, comboEditor);
            rowEditor.setEditorAt(TableFields.ROW_DIMMED_GRAPH, checkEditor);
            rowEditor.setEditorAt(TableFields.ROW_HIDDEN_GRAPH, checkEditor);
            rowEditor.setEditorAt(TableFields.ROW_VARIABLESIZE_GRAPH, checkEditor);
        }


    }

    private StateSize getStateSize(JComboBox comboBox) {
        int selectedItem = comboBox.getSelectedIndex();
        StateSize stateSize;

        switch (selectedItem) {
            case 0:
                stateSize = StateSize.VERY_SMALL;
                break;
            case 1:
                stateSize = StateSize.SMALL;
                break;
            case 2:
                stateSize = StateSize.MEDIUM;
                break;
            case 3:
                stateSize = StateSize.LARGE;
                break;
            default:
                stateSize = StateSize.MEDIUM;
                break;
        }
        return stateSize;
    }

    private void setAttributesInState(int row, boolean selected) {

        vGraphCell cell = (vGraphCell) canvas.getSelectionCell();

        if (cell instanceof State) {

            State state = (State) cell;
            ProjectType type = state.getType();

            // Autômato
            if (type == ProjectType.AUTOMATON) {

                if (row == TableFields.ROW_INITIAL) {
                    state.setInitial(selected);
                    State newState = new State(state);
                    newState.setNode(state.getInspectorNode());
                    state.getInspectorNode().setCell(newState);
                    Object[] cells = {state};
                    cells = canvas.getDescendants(cells);
                    canvas.getModel().remove(cells);
                    canvas.getGraphLayoutCache().insert(newState);
                    canvas.setSelectionCell(newState);
                }
                if (row == TableFields.ROW_FINAL) {
                    state.setFinal(selected);
                }
                if (row == TableFields.ROW_DIMMED_AUTOMATON) {
                    state.setDimmed(selected);
                }
                if (row == TableFields.ROW_HIDDEN_AUTOMATON) {
                    state.setHidden(selected);
                }
                if (row == TableFields.ROW_VARIABLESIZE_AUTOMATON) {
                    //GraphConstants.setSizeable(cell.getAttributes(), selected);
                }

            } else { // Grafo

                if (row == TableFields.ROW_DIMMED_GRAPH) {
                    state.setDimmed(selected);
                }
                if (row == TableFields.ROW_HIDDEN_GRAPH) {
                    state.setHidden(selected);
                }
                if (row == TableFields.ROW_VARIABLESIZE_GRAPH) {
                    //GraphConstants.setSizeable(cell.getAttributes(), selected);
                }
            }
        }
        canvas.refresh();
        canvas.getGraphLayoutCache().reload();
    }

    public void insertData(Object cell) {
        // Insere informações de um estado
        if (canvas.getProject().getType() == ProjectType.AUTOMATON) {
            if (cell != null && cell instanceof State) {

                State state = (State) cell;

                setValueAt(state.getName(),
                        TableFields.ROW_NAME, TableFields.COLUMN_VALUE);

                setValueAt(state.getSize(),
                        TableFields.ROW_SIZE, TableFields.COLUMN_VALUE);

                setValueAt(state.isInitial(),
                        TableFields.ROW_INITIAL, TableFields.COLUMN_VALUE);

                setValueAt(state.isFinal(),
                        TableFields.ROW_FINAL, TableFields.COLUMN_VALUE);

                setValueAt(state.isDimmed(),
                        TableFields.ROW_DIMMED_AUTOMATON, TableFields.COLUMN_VALUE);

                setValueAt(state.isHidden(),
                        TableFields.ROW_HIDDEN_AUTOMATON, TableFields.COLUMN_VALUE);

                setValueAt(state.isVariableSize(),
                        TableFields.ROW_VARIABLESIZE_AUTOMATON, TableFields.COLUMN_VALUE);

                setValueAt(state.getX(),
                        TableFields.ROW_POSX_AUTOMATON, TableFields.COLUMN_VALUE);

                setValueAt(state.getY(),
                        TableFields.ROW_POSY_AUTOMATON, TableFields.COLUMN_VALUE);

            } // Insere informações de uma transição
            else if (cell != null && cell instanceof Transition) {
            } // Limpa os campos da tabela
            else {
                setValueAt(null, TableFields.ROW_NAME,
                        TableFields.COLUMN_VALUE);

                setValueAt(null, TableFields.ROW_SIZE,
                        TableFields.COLUMN_VALUE);

                setValueAt(null, TableFields.ROW_INITIAL,
                        TableFields.COLUMN_VALUE);

                setValueAt(null, TableFields.ROW_FINAL,
                        TableFields.COLUMN_VALUE);

                setValueAt(null, TableFields.ROW_DIMMED_AUTOMATON,
                        TableFields.COLUMN_VALUE);

                setValueAt(null, TableFields.ROW_HIDDEN_AUTOMATON,
                        TableFields.COLUMN_VALUE);

                setValueAt(null, TableFields.ROW_VARIABLESIZE_AUTOMATON,
                        TableFields.COLUMN_VALUE);

                setValueAt(null, TableFields.ROW_POSX_AUTOMATON,
                        TableFields.COLUMN_VALUE);

                setValueAt(null, TableFields.ROW_POSY_AUTOMATON,
                        TableFields.COLUMN_VALUE);
            }

        } else if (canvas.getProject().getType() == ProjectType.GRAPH_DIRECTED
                || canvas.getProject().getType() == ProjectType.GRAPH_UNDIRECTED) {

            if (cell != null && cell instanceof State) {

                State state = (State) cell;

                setValueAt(state.getName(),
                        TableFields.ROW_NAME, TableFields.COLUMN_VALUE);

                setValueAt(state.getSize(),
                        TableFields.ROW_SIZE, TableFields.COLUMN_VALUE);

                setValueAt(state.isDimmed(),
                        TableFields.ROW_DIMMED_GRAPH, TableFields.COLUMN_VALUE);

                setValueAt(state.isHidden(),
                        TableFields.ROW_HIDDEN_GRAPH, TableFields.COLUMN_VALUE);

                setValueAt(state.isVariableSize(),
                        TableFields.ROW_VARIABLESIZE_GRAPH, TableFields.COLUMN_VALUE);

                setValueAt(state.getX(),
                        TableFields.ROW_POSX_GRAPH, TableFields.COLUMN_VALUE);

                setValueAt(state.getY(),
                        TableFields.ROW_POSY_GRAPH, TableFields.COLUMN_VALUE);

            } // Insere informações de uma transição
            else if (cell != null && cell instanceof Transition) {
            } // Limpa os campos da tabela
            else {
                setValueAt(null, TableFields.ROW_NAME,
                        TableFields.COLUMN_VALUE);

                setValueAt(null, TableFields.ROW_SIZE,
                        TableFields.COLUMN_VALUE);

                setValueAt(null, TableFields.ROW_DIMMED_GRAPH,
                        TableFields.COLUMN_VALUE);

                setValueAt(null, TableFields.ROW_HIDDEN_GRAPH,
                        TableFields.COLUMN_VALUE);

                setValueAt(null, TableFields.ROW_VARIABLESIZE_GRAPH,
                        TableFields.COLUMN_VALUE);

                setValueAt(null, TableFields.ROW_POSX_GRAPH,
                        TableFields.COLUMN_VALUE);

                setValueAt(null, TableFields.ROW_POSY_GRAPH,
                        TableFields.COLUMN_VALUE);
            }
        }
    }
}
