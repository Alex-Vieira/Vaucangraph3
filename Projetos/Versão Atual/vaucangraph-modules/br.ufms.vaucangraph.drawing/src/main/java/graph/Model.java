package graph;

import javafx.scene.control.Label;
import shape.CircleCell;
import shape.RectangleCell;
import shape.TriangleCell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {

    private Vertex graphParent;

    private List<Vertex> allCells;
    private List<Vertex> addedCells;
    private List<Vertex> removedCells;

    private List<Edge> allEdges;
    private List<Edge> addedEdges;
    private List<Edge> removedEdges;

    private Map<String,Vertex> cellMap; // <id,cell>

    public Model() {
        graphParent = new Vertex( "_ROOT_", "");

        // clear model, create lists
        clear();

    }

    public void clear() {

        allCells = new ArrayList<>();
        addedCells = new ArrayList<>();
        removedCells = new ArrayList<>();

        allEdges = new ArrayList<>();
        addedEdges = new ArrayList<>();
        removedEdges = new ArrayList<>();

        cellMap = new HashMap<>(); // <id,cell>
    }

    public void clearAddedLists() {
        addedCells.clear();
        addedEdges.clear();
    }

    public List<Vertex> getAddedCells() {
        return addedCells;
    }

    public List<Vertex> getRemovedCells() {
        return removedCells;
    }

    public List<Vertex> getAllCells() {
        return allCells;
    }

    public List<Edge> getAddedEdges() {
        return addedEdges;
    }

    public List<Edge> getRemovedEdges() {
        return removedEdges;
    }

    public List<Edge> getAllEdges() {
        return allEdges;
    }

    public void addCell(String id, String rotulo, CellType type, double x, double y) {
        Label l = new Label("");
        switch (type) {

            case RECTANGLE:
                RectangleCell rectangleCell = new RectangleCell(id, rotulo);
                addCell(rectangleCell);
                break;

            case CIRCLE:
                CircleCell circleCell = new CircleCell(id, rotulo);

                addCell(circleCell);
                break;

            case TRIANGLE:
                TriangleCell triangleCell = new TriangleCell(id, rotulo);
                addCell(triangleCell);
                break;

            default:
                throw new UnsupportedOperationException("Unsupported type: " + type);
        }
    }

    public Vertex addCell(Vertex cell) {
        addedCells.add(cell);
        cellMap.put(cell.getCellId(), cell);
        return cell;
    }

    public void addEdge( String sourceId, String targetId, EdgeType type) {

        Vertex sourceCell = cellMap.get( sourceId);
        Vertex targetCell = cellMap.get( targetId);

        Edge edge = new Edge( sourceCell, targetCell, type);

        addedEdges.add(edge);
    }

    public void merge() {

        // cells
        allCells.addAll( addedCells);
        allCells.removeAll( removedCells);

        addedCells.clear();
        removedCells.clear();

        // edges
        allEdges.addAll( addedEdges);
        allEdges.removeAll( removedEdges);

        addedEdges.clear();
        removedEdges.clear();
    }

}