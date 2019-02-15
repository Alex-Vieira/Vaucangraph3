package br.ufms.vaucangraph.core;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProjectProperty {

    private StringProperty projectName;
    private DoubleProperty width = new SimpleDoubleProperty(0.0);
    private DoubleProperty height = new SimpleDoubleProperty(0.0);
    private ProjectType projectType;
    private GraphProperty graph;

    public ProjectProperty(String projectName, Double width, Double height, ProjectType projectType) {
        this.projectName = new SimpleStringProperty(projectName);
        this.width = new SimpleDoubleProperty(width);
        this.height = new SimpleDoubleProperty(height);
        this.projectType = projectType;
        this.graph = new GraphProperty();
    }

    public String getProjectName() {
        return projectName.get();
    }

    public StringProperty projectNameProperty() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName.set(projectName);
    }

    public double getWidth() {
        return width.get();
    }

    public DoubleProperty widthProperty() {
        return width;
    }

    public void setWidth(double width) {
        this.width.set(width);
    }

    public double getHeight() {
        return height.get();
    }

    public DoubleProperty heightProperty() {
        return height;
    }

    public void setHeight(double height) {
        this.height.set(height);
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public GraphProperty getGraph() {
        return graph;
    }

    public void setGraph(GraphProperty graph) {
        this.graph = graph;
    }
}
