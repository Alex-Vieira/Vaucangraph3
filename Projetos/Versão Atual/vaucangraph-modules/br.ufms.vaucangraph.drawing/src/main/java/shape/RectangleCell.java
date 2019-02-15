package shape;

import graph.Vertex;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectangleCell extends Vertex {

    public RectangleCell(String id, String rotulo) {
        super(id, rotulo);

        Rectangle view = new Rectangle(50, 50);
        view.setStrokeWidth(3);
        view.setStroke(Color.DARKSLATEBLUE);
        view.setFill(Color.YELLOWGREEN);

        setView(view);

        xProperty().bind(layoutXProperty().add(getBoundsInLocal().getMaxX()).divide(2));
        yProperty().bind(layoutYProperty().add(getBoundsInLocal().getMaxY()).divide(2));

        System.out.println(view.getHeight());
    }

}
