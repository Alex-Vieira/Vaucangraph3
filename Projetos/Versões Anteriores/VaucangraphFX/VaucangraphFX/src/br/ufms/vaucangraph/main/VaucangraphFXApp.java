/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.vaucangraph.main;

import br.ufms.vaucangraph.view.javafx.controls.ResizableScene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author kleberkruger
 */
public class VaucangraphFXApp extends Application {

    private final double shadowSize = 10;

    /**
     * Creates a shadow effect as a halo around the pane and not within the pane's content area.
     */
    private Pane createShadowPane() {

        Pane shadowPane = new Pane();

        // a "real" app would do this in a CSS stylesheet.
        shadowPane.setStyle(
                "-fx-background-color: darkgray;"
                        + "-fx-effect: dropshadow(gaussian, red, " + shadowSize + ", 0, 0, 0);"
                        + "-fx-background-insets: " + shadowSize + ";"
        );

//        shadowPane.layoutBoundsProperty().addListener((observable, oldBounds, newBounds) -> {
//
//            Rectangle innerRect = new Rectangle();
//            Rectangle outerRect = new Rectangle();
//
//            innerRect.relocate(newBounds.getMinX() + shadowSize, newBounds.getMinY() + shadowSize);
//            innerRect.setWidth(newBounds.getWidth() - shadowSize * 2);
//            innerRect.setHeight(newBounds.getHeight() - shadowSize * 2);
//
//            outerRect.setWidth(newBounds.getWidth());
//            outerRect.setHeight(newBounds.getHeight());
//
//            Shape clip = Shape.subtract(outerRect, innerRect);
//            shadowPane.setClip(clip);
//        });
        return shadowPane;
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader();

        Parent root = (Parent) loader.load(getClass().getClassLoader().getResourceAsStream(
                "br/ufms/vaucangraph/view/javafx/fxml/VaucangraphFX.fxml"));

//        StackPane stackPane = new StackPane(createShadowPane());
//        stackPane.setStyle(
//                "-fx-background-color: rgba(255, 255, 255, 0.5);"
//                + "-fx-background-insets: " + shadowSize + ";"
//        );

//        stackPane.getChildren().add(root);
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        root.setStyle("-fx-background-color: red;"); // just to see the limit of the window ;-)

//        AnchorPane background = new AnchorPane(root);
//        background.setStyle(
//                "-fx-background-color: rgba(64, 64, 64, 0.0);"
////                + "-fx-effect: dropshadow(gaussian, red, 50, 0, 0, 0);"
////                + "-fx-background-insets: 50;"
//        );
//
//        AnchorPane.setTopAnchor(root, 1.0);
//        AnchorPane.setBottomAnchor(root, 1.0);
//        AnchorPane.setLeftAnchor(root, 1.0);
//        AnchorPane.setRightAnchor(root, 1.0);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        double width = screenBounds.getWidth() - 100;
        double height = screenBounds.getHeight() - 100;

        ResizableScene scene = new ResizableScene(root, width, height);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(getClass().getClassLoader().getResource(
                "br/ufms/vaucangraph/view/javafx/css/vaucangraph-default.css").toExternalForm());

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setTitle("VaucangraphFX");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Platform.setImplicitExit(false);

        launch(args);
    }

}
