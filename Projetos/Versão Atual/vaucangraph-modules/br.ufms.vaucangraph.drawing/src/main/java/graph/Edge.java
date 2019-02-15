package graph;

import br.ufms.vaucangraph.core.VertexProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.shape.*;

public class Edge extends Group {

    private Vertex source;
    private Vertex target;

    private Line line;
    private Path path;
    private Polygon triangle;

    private DoubleProperty distanceAB = new SimpleDoubleProperty();
    private DoubleProperty calc = new SimpleDoubleProperty();
    private DoubleProperty vectorABX = new SimpleDoubleProperty();
    private DoubleProperty vectorABY = new SimpleDoubleProperty();
    private DoubleProperty vectorRadiusX = new SimpleDoubleProperty();
    private DoubleProperty vectorRadiusY = new SimpleDoubleProperty();

    private DoubleProperty dx = new SimpleDoubleProperty();
    private DoubleProperty dy = new SimpleDoubleProperty();
    private DoubleProperty angle = new SimpleDoubleProperty();

    public Edge(Vertex source, Vertex target, EdgeType type) {
        Label l = new Label("text");
        source.boundsInParentProperty().addListener((observable, oldValue, newValue) -> {
            calcProperty().setValue(source.getPosition().distance(target.getPosition()));
            angleProperty().setValue((Math.atan2(dyProperty().getValue(), dxProperty().getValue()) * 180 / Math.PI));
        });

        target.boundsInParentProperty().addListener((observable, oldValue, newValue) -> {
            calcProperty().setValue(source.getPosition().distance(target.getPosition()));
            angleProperty().setValue((Math.atan2(dyProperty().getValue(), dxProperty().getValue()) * 180 / Math.PI));
        });

        this.source = source;
        this.target = target;

        switch (type) {

            case LINE:
                source.getEdges().add(this);
                // target.addCellParent(source);
                line = new Line();
                line.getStyleClass().add("line");
//                line.setStrokeWidth(2);

                triangle = new Polygon(-5, -4, -5, 4, 5, 0);

                line.startXProperty().bind(source.layoutXProperty().add(source.getBoundsInParent().getMaxX() / 2.05).add(vectorRadiusXProperty()));
                line.startYProperty().bind(source.layoutYProperty().add(source.getBoundsInParent().getMaxY() / 2.05).add(vectorRadiusYProperty()));
                line.endXProperty().bind(target.layoutXProperty().add(target.getBoundsInParent().getMaxX() / 1.9).subtract(vectorRadiusXProperty()));
                line.endYProperty().bind(target.layoutYProperty().add(target.getBoundsInParent().getMaxY() / 1.9).subtract(vectorRadiusYProperty()));

                triangle.layoutXProperty().bind(line.endXProperty());
                triangle.layoutYProperty().bind(line.endYProperty());


                dxProperty().bind(line.endXProperty().subtract(line.startXProperty()));
                dyProperty().bind(line.endYProperty().subtract(line.startYProperty()));

                triangle.rotateProperty().bind(angleProperty());

                line.visibleProperty().bind(distanceABProperty().greaterThan(50.0));
                triangle.visibleProperty().bind(distanceABProperty().greaterThan(50.0));

                l.visibleProperty().bind(distanceABProperty().greaterThan(50.0));


                getChildren().addAll(l, line, triangle);

                break;

            case ARC:
//                source.addCellChild(target);
//                target.addCellParent(source);
                source.getEdges().add(this);
                path = new Path();
                ArcTo arc = new ArcTo();
                MoveTo moveTo = new MoveTo();
//                path.setStrokeWidth(2);

                triangle = new Polygon(-5, -4, -5, 4, 5, 0);
                moveTo.xProperty().bind(source.layoutXProperty().add(source.getBoundsInLocal().getMaxX() / 2).add(vectorRadiusXProperty()));
                moveTo.yProperty().bind(source.layoutYProperty().add(source.getBoundsInLocal().getMaxY() / 2).add(vectorRadiusYProperty()));
                arc.xProperty().bind(target.layoutXProperty().add(target.getBoundsInLocal().getMaxX() / 1.9).subtract(vectorRadiusXProperty()));
                arc.yProperty().bind(target.layoutYProperty().add(target.getBoundsInLocal().getMaxY() / 1.9).subtract(vectorRadiusYProperty()));

                arc.radiusXProperty().bind(distanceAB.multiply(1.1));
                arc.radiusYProperty().bind(distanceAB.multiply(1.1));

                triangle.rotateProperty().bind(angleProperty().subtract(20));

                triangle.layoutXProperty().bind(arc.xProperty());
                triangle.layoutYProperty().bind(arc.yProperty());

                dxProperty().bind(arc.xProperty().subtract(moveTo.xProperty()));
                dyProperty().bind(arc.yProperty().subtract(moveTo.yProperty()));

                path.getElements().addAll(moveTo, arc);

                path.visibleProperty().bind(distanceABProperty().greaterThan(25.0));
                triangle.visibleProperty().bind(distanceABProperty().greaterThan(25.0));
                getChildren().addAll(path, triangle);

                break;

            case LOOP:
//                source.addCellChild(target);
//                target.addCellParent(source);
                source.getEdges().add(this);
                path = new Path();
                LineTo lineto = new LineTo();
                MoveTo moveTo2 = new MoveTo();
//                path.setStrokeWidth(2);

                triangle = new Polygon(-5, -4, -5, 4, 5, 0);
                moveTo2.xProperty().bind(source.layoutXProperty().add(source.getBoundsInLocal().getMaxX() / 2).add(vectorRadiusXProperty()));
                moveTo2.yProperty().bind(source.layoutYProperty().add(source.getBoundsInLocal().getMaxY() / 2).add(vectorRadiusYProperty()));
                lineto.xProperty().bind(target.layoutXProperty().add(target.getBoundsInLocal().getMaxX() / 1.9).subtract(vectorRadiusXProperty()));
                lineto.yProperty().bind(target.layoutYProperty().add(target.getBoundsInLocal().getMaxY() / 1.9).subtract(vectorRadiusYProperty()));

                triangle.layoutXProperty().bind(lineto.xProperty());
                triangle.layoutYProperty().bind(lineto.yProperty());

                triangle.rotateProperty().bind(angleProperty());

                dxProperty().bind(lineto.xProperty().subtract(moveTo2.xProperty()));
                dyProperty().bind(lineto.yProperty().subtract(moveTo2.yProperty()));

                path.getElements().add(moveTo2);
                path.getElements().add(lineto);

                getChildren().addAll(path, triangle);

                break;
            default:
                throw new UnsupportedOperationException("Unsupported type: " + type);
        }

        distanceABProperty().bind(calcProperty());
        vectorABXProperty().bind(target.xProperty().subtract(source.xProperty()));
        vectorABYProperty().bind(target.yProperty().subtract(source.yProperty()));
        vectorRadiusXProperty().bind(calcProperty().add(vectorABXProperty().multiply(25.0)).divide(distanceABProperty()));
        vectorRadiusYProperty().bind(calcProperty().add(vectorABYProperty().multiply(25.0)).divide(distanceABProperty()));
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getTarget() {
        return target;
    }

    public DoubleProperty vectorABXProperty() {
        return vectorABX;
    }

    public DoubleProperty vectorABYProperty() {
        return vectorABY;
    }

    public DoubleProperty vectorRadiusXProperty() {
        return vectorRadiusX;
    }

    public DoubleProperty vectorRadiusYProperty() {
        return vectorRadiusY;
    }

    public DoubleProperty distanceABProperty() {
        return distanceAB;
    }

    public DoubleProperty calcProperty() {
        return calc;
    }

    public DoubleProperty dxProperty() {
        return dx;
    }

    public DoubleProperty getDx() {
        return dx;
    }

    public void setDx(DoubleProperty dx) {
        this.dx = dx;
    }

    public DoubleProperty dyProperty() {
        return dy;
    }

    public DoubleProperty getDy() {
        return dy;
    }

    public void setDy(DoubleProperty dy) {
        this.dy = dy;
    }

    public DoubleProperty angleProperty() {
        return angle;
    }

    public DoubleProperty getAngle() {
        return angle;
    }

    public void setAngle(DoubleProperty angle) {
        this.angle = angle;
    }

}
