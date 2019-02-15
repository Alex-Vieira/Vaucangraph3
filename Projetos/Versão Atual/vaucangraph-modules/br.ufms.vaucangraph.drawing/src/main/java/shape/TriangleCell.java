package shape;

import graph.Vertex;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class TriangleCell extends Vertex {

    public TriangleCell(String id, String rotulo) {
        super(id, rotulo);

        double width = 50;
        double height = 50;

        Polygon view = new Polygon(width / 2, 0, width, height, 0, height);
        view.setStrokeWidth(3);
        view.setStroke(Color.DARKSLATEBLUE);
        view.setFill(Color.RED);

        setView(view);

        xProperty().bind(layoutXProperty().add(getBoundsInLocal().getMaxX()).divide(2));
        yProperty().bind(layoutYProperty().add(getBoundsInLocal().getMaxY()).divide(2));
    }

}
