package jgraph.view;

import java.awt.Component;
import org.jgraph.JGraph;
import org.jgraph.graph.CellView;
import org.jgraph.graph.VertexRenderer;
import vaucansong.bean.State;
import vaucansong.view.StateView;

/**
 * Classe responsável pela renderização da célula
 * @author Kleber Kruger
 */
public class vVertexRenderer extends VertexRenderer {

    public vVertexRenderer() {
        super();
    }

    @Override
    public Component getRendererComponent(JGraph graph, CellView view,
            boolean selected, boolean focused, boolean preview) {

        Canvas canvas = (Canvas) graph;
        Object obj = view.getCell();

        if (obj instanceof State) {

            State state = (State) obj;

            // Informações do vértice/estado
            state.setX((int) view.getBounds().getX());
            state.setY((int) view.getBounds().getY());

            // Informações da célula
            state.setSelected(selected);
            state.setFocused(state.isFocused());

            if (state.isNameChanged()) {
                // Atualiza o nó do inspector
                canvas.getInspector().refresh();
                state.setNameChanged(false);
            }

            return new StateView(state);
        }

        return super.getRendererComponent(canvas, view, selected, focused, preview);
    }
}
