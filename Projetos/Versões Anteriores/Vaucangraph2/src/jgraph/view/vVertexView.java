/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgraph.view;

import org.jgraph.graph.VertexView;
import vaucansong.bean.State;

/**
 *
 * @author Kleber Kruger
 */
public class vVertexView extends VertexView {

    public vVertexView(Object cell) {
        super(cell);

        if (cell instanceof State) {

            /**
             * Renderiza a célula de estado de acordo com uma StateView
             * renderer - atributo da superclasse do tipo VertexRenderer
             */
            renderer = new vVertexRenderer();
        }
    }
}
