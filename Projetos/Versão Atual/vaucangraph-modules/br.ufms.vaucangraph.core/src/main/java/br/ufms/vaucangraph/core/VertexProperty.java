package br.ufms.vaucangraph.core;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;

import java.util.Map;

public class VertexProperty {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private DoubleProperty x  = new SimpleDoubleProperty(200.0);
    private DoubleProperty y  = new SimpleDoubleProperty(200.0);
    private VertexSize size;
    private ObservableList<EdgeProperty> adjacencyMap = FXCollections.observableArrayList();


    public VertexProperty(IntegerProperty id, StringProperty name, DoubleProperty x, DoubleProperty y, VertexSize size, ObservableList<EdgeProperty> adjacencyMap) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.size = size;
        this.adjacencyMap = adjacencyMap;
    }

    public VertexProperty() {
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getX() {
        return x.get();
    }

    public DoubleProperty xProperty() {
        return x;
    }

    public void setX(double x) {
        this.x.set(x);
    }

    public double getY() {
        return y.get();
    }

    public DoubleProperty yProperty() {
        return y;
    }

    public void setY(double y) {
        this.y.set(y);
    }

    public VertexSize getSize() {
        return size;
    }

    public void setSize(VertexSize size) {
        this.size = size;
    }

    public ObservableList<EdgeProperty> getAdjacencyMap() {
        return adjacencyMap;
    }

    public void setAdjacencyMap(ObservableList< EdgeProperty> adjacencyMap) {
        this.adjacencyMap = adjacencyMap;
    }
}
