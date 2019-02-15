package br.ufms.vaucangraph.app.statusbar;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class StatusbarView implements FxmlView<StatusbarViewModel>, Initializable {

    @FXML
    private ToggleButton tgbDesign;
    @FXML
    private ToggleButton tgbVaucanson;
    @FXML
    private Label lblPosition;
    @InjectViewModel
    private StatusbarViewModel viewModel;

    private DoubleProperty mouseX = new SimpleDoubleProperty();
    private DoubleProperty mouseY = new SimpleDoubleProperty();
    private DoubleProperty width = new SimpleDoubleProperty();
    private DoubleProperty height = new SimpleDoubleProperty();
    private DoubleProperty elementX = new SimpleDoubleProperty();
    private DoubleProperty elementY = new SimpleDoubleProperty();
    private StringProperty sourceName = new SimpleStringProperty();
    private StringProperty targetName = new SimpleStringProperty();

    private ToggleGroup tg = new ToggleGroup();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tg.getToggles().addAll(tgbDesign, tgbVaucanson);

//       lblPosition.textProperty().bind(mouse.getSceneX());
//        StringConverter sc = new NumberStringConverter();
//        NumberBinding sum = Bindings.add(mouse.getSceneX());
//       Bindings.bindBidirectional(lblPosition.textProperty(), , sc);
    }

    public double getMouseX() {
        return mouseX.get();
    }

    public DoubleProperty mouseXProperty() {
        return mouseX;
    }

    public void setMouseX(double mouseX) {
        this.mouseX.set(mouseX);
    }

    public double getMouseY() {
        return mouseY.get();
    }

    public DoubleProperty mouseYProperty() {
        return mouseY;
    }

    public void setMouseY(double mouseY) {
        this.mouseY.set(mouseY);
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

    public double getElementX() {
        return elementX.get();
    }

    public DoubleProperty elementXProperty() {
        return elementX;
    }

    public void setElementX(double elementX) {
        this.elementX.set(elementX);
    }

    public double getElementY() {
        return elementY.get();
    }

    public DoubleProperty elementYProperty() {
        return elementY;
    }

    public void setElementY(double elementY) {
        this.elementY.set(elementY);
    }

    public String getSourceName() {
        return sourceName.get();
    }

    public StringProperty sourceNameProperty() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName.set(sourceName);
    }

    public String getTargetName() {
        return targetName.get();
    }

    public StringProperty targetNameProperty() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName.set(targetName);
    }


}
