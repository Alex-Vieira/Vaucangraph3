package br.ufms.vaucangraph.app.toolbar;

import br.ufms.vaucangraph.core.ProjectProperty;
import br.ufms.vaucangraph.core.ProjectType;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class ToolbarViewModel implements ViewModel {


    private ProjectProperty projectProperty ;

    private DoubleProperty width = new SimpleDoubleProperty(0.0);
    private DoubleProperty height = new SimpleDoubleProperty(0.0);


    public ToolbarViewModel() {
        projectProperty = new ProjectProperty("Teste", 200.00, 200.00, ProjectType.GRAPH);
        width.bindBidirectional(projectProperty.widthProperty());
        height.bindBidirectional(projectProperty.heightProperty());
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
