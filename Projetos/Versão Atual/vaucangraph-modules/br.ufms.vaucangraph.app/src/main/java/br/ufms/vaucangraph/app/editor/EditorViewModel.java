package br.ufms.vaucangraph.app.editor;

import br.ufms.vaucangraph.core.ProjectProperty;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class EditorViewModel implements ViewModel {
    private ProjectProperty projectProperty;
    private DoubleProperty width = new SimpleDoubleProperty(500.0);
    private DoubleProperty height = new SimpleDoubleProperty(100.0);

    public EditorViewModel() {

    }

    public ProjectProperty getProjectProperty() {
        return projectProperty;
    }

    public void setProjectProperty(ProjectProperty projectProperty) {
        this.projectProperty = projectProperty;
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
}
