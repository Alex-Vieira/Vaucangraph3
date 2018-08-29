/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgraph.model;

import jgraph.view.vVertexView;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.VertexView;

/**
 *
 * @author Kleber Kruger
 */
public class vCellViewFactory extends DefaultCellViewFactory {

    @Override
    protected VertexView createVertexView(Object cell) {
        return new vVertexView(cell);
    }

}
