package layout.random;

import graph.Graph;
import graph.Vertex;
import layout.base.Layout;

import java.util.List;
import java.util.Random;

public class PositionLayout extends Layout {

    Graph graph;

    Random rnd = new Random();
    double x;
    double y;

    public PositionLayout(Graph graph) {

        this.graph = graph;

    }

    public void execute() {

        List<Vertex> cells = graph.getModel().getAllCells();

        for (Vertex cell : cells) {

            x = cell.xProperty().getValue();
            y = cell.yProperty().getValue();
            cell.relocate(x, y);
            System.out.println("Cell layout: " + cell.getCellId() +" X :"+cell.getX()+" Y :"+cell.getY());
        }

    }

}
