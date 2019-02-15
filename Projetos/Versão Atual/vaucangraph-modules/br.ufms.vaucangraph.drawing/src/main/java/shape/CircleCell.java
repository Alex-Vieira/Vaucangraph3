package shape;

import graph.Vertex;
import javafx.scene.shape.Circle;

public class CircleCell extends Vertex {
    public CircleCell(String id, String name) {
        super(id, name);

        Circle view = new Circle(25, 25, 25);
        view.getStyleClass().add("circle");
        setView(view);

//        setOnMouseClicked(t -> {
//            if (t.getClickCount() == 2) {
//                super.getTextField().setVisible(!super.getTextField().visibleProperty().getValue());
//            }
//        });

        xProperty().bind(layoutXProperty().add(getBoundsInParent().getMaxX()));
        yProperty().bind(layoutYProperty().add(getBoundsInParent().getMaxY()));



    }

}