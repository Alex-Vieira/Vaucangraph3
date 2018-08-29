package jgraph.model;

import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.Edge;
import vaucangraph.view.ProjectPanel;

/**
 * Modelo de grafo personalizado Vaucangraph
 * @author Kleber Kruger
 */
public class vGraphModel extends DefaultGraphModel {

    private ProjectPanel projectPanel;

    public vGraphModel(ProjectPanel panel) {
        super();
        this.projectPanel = panel;
    }

    @Override
    public boolean acceptsSource(Object edge, Object port) {
        // Source only Valid if not Equal Target
        return (((Edge) edge).getTarget() != port);
    }

    @Override
    public boolean acceptsTarget(Object edge, Object port) {
        // Permite a transição para si mesmo
        if (projectPanel.getJtbSelfTransition().isSelected()) {
            return super.acceptsTarget(edge, port);
        }
        // Transição é válida apenas se o alvo for outra célula
        return (((Edge) edge).getSource() != port);
    }
}
