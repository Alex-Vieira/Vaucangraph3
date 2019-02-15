package br.ufms.vaucangraph.core;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GraphProperty {
    private GraphType graphType;
    private ObservableList<VertexProperty> vertexList= FXCollections.observableArrayList();


    public GraphProperty() {
//        vertexList = FXCollections.observableArrayList();
    }

    public GraphType getGraphType() {
        return graphType;
    }

    public void setGraphType(GraphType graphType) {
        this.graphType = graphType;
    }

    public ObservableList<VertexProperty> getVertexList() {
        return vertexList;
    }

    public void setVertexList(ObservableList<VertexProperty> vertexList) {
        this.vertexList = vertexList;
    }
}
