package vaucangraph.components.inspector;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import jgraph.model.vMutableTreeNode;
import jgraph.view.Canvas;
import utils.state.StateUtils;
import vaucangraph.view.Vaucangraph;
import vaucansong.bean.State;
import vaucansong.bean.Transition;

/**
 * Renderiza os componentes do inspector
 * @author Kleber Kruger
 */
public class InspectorCellRenderer extends DefaultTreeCellRenderer {

    // Ícones do inspector:
    private final ImageIcon vaucangraphIcon = new ImageIcon(InspectorCellRenderer.class.getResource(
            "/vaucangraph/icons/inspector-vaucangraph.png"));
    private final ImageIcon stateIcon = new ImageIcon(InspectorCellRenderer.class.getResource(
            "/vaucangraph/icons/inspector-state.png"));
    private final ImageIcon transitionIcon = new ImageIcon(InspectorCellRenderer.class.getResource(
            "/vaucangraph/icons/inspector-transition.png"));

    public InspectorCellRenderer(JTree tree) {
        super();
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean selected, boolean expanded, boolean leaf, int row, boolean focused) {

        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, focused);

        JVInspector inspector = (JVInspector) tree;
        Canvas canvas = inspector.getCanvas();

        // Células personalizadas
        if (value instanceof vMutableTreeNode) {

            vMutableTreeNode node = (vMutableTreeNode) value;

            // Altera o ícone conforme o tipo do nó (estado ou transição)
            if (node.getCell() instanceof State) {
                setIcon(stateIcon);
                setClosedIcon(stateIcon);
                setLeafIcon(stateIcon);
                setOpenIcon(stateIcon);

                if (node.getCell().isNameChanged()) {
                    canvas.getGraphLayoutCache().refresh(StateUtils.getCellView(
                            node.getCell(), canvas), true);
                    // Repinta o canvas
                    canvas.refresh();
                    node.getCell().setNameChanged(false);
                }
            }

            if (node.getCell() instanceof Transition) {
                setIcon(transitionIcon);
                setClosedIcon(transitionIcon);
                setLeafIcon(transitionIcon);
                setOpenIcon(transitionIcon);

                if (node.getCell().isNameChanged()) {
                    canvas.getGraphLayoutCache().refresh(StateUtils.getCellView(
                            node.getCell(), canvas), true);
                    // Repinta o canvas
                    canvas.refresh();
                    node.getCell().setNameChanged(false);
                }
            }

        } else {
            // Se não for nó de estado ou transição, renderiza o nó com o ícone padrão
            setIcon(vaucangraphIcon);
            setClosedIcon(vaucangraphIcon);
            setLeafIcon(vaucangraphIcon);
            setOpenIcon(vaucangraphIcon);
        }

        if (canvas != null) {
            // Se precisar, altera o nome do projeto
            if (!inspector.getRoot().getUserObject().equals(
                    canvas.getProject().getName())) {

                Vaucangraph vcg = canvas.getProjectPanel().getVaucangraph();
                String newProjectName = inspector.getRoot().getUserObject().toString();
                // Altera o nome do projeto
                canvas.getProject().setName(newProjectName);
                vcg.getJtpProjects().setTitleAt(
                        // Aba do projeto, novo nome do projeto
                        vcg.getJtpProjects().getSelectedIndex(), newProjectName);
            }
        }

        return this;
    }
}
