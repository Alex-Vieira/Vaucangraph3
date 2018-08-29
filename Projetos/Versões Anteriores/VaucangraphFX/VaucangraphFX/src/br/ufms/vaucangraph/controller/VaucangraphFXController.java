/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.vaucangraph.controller;

import br.ufms.vaucangraph.view.swing.MyEditorRuler;
import br.ufms.vaucangraph.view.swing.MyScrollBarUI;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxSwingConstants;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.SwingUtilities;



/**
 * FXML Controller class
 *
 * @author kleberkruger
 */
public class VaucangraphFXController implements Initializable {

    private Rectangle2D backupWindowBounds = null;
    private boolean maximized = false;

    private double mouseDragOffsetX = 0;
    private double mouseDragOffsetY = 0;

    @FXML
    private HBox titleBarHBox;

    @FXML
    private TabPane projectTabPane;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        titleBarHBox.getChildren().stream().forEach(node -> {
            createWindowDragging(node);
        });

//        try {
//
//            FXMLLoader loader = new FXMLLoader();
//
//            Parent root = (Parent) loader.load(getClass().getClassLoader().getResourceAsStream(
//                    "br/ufms/vaucangraph/view/javafx/fxml/ProjectPaneSwing.fxml"));
//
//            Tab tab = new Tab();
//            tab.setContent(root);
//            tab.setText("Project without title 1");
//
//            projectTabPane.getTabs().add(tab);
//
//        } catch (IOException ex) {
//            Logger.getLogger(VaucangraphFXController.class.getName()).log(Level.SEVERE, null, ex);
//        }

        Tab tab = new Tab("New project");
        tab.setContent(createSwingContent());

        projectTabPane.getTabs().add(tab);
    }

    @FXML
    private void minimizeStage(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void maximizeStage(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        toogleMaximized(stage);
    }

    @FXML
    private void exitStage(ActionEvent event) {
        Platform.exit();
    }

    private void toogleMaximized(Stage stage) {
        final Screen screen = Screen.getScreensForRectangle(stage.getX(), stage.getY(), 1, 1).get(0);
        if (maximized) {
            maximized = false;
            if (backupWindowBounds != null) {
                stage.setX(backupWindowBounds.getMinX());
                stage.setY(backupWindowBounds.getMinY());
                stage.setWidth(backupWindowBounds.getWidth());
                stage.setHeight(backupWindowBounds.getHeight());
            }
        } else {
            maximized = true;
            backupWindowBounds = new Rectangle2D(stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight());
            stage.setX(screen.getVisualBounds().getMinX());
            stage.setY(screen.getVisualBounds().getMinY());
            stage.setWidth(screen.getVisualBounds().getWidth());
            stage.setHeight(screen.getVisualBounds().getHeight());
        }
    }

    private void createWindowDragging(Node node) {
            // add window header double clicking
            node.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 2) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                toogleMaximized(stage);
            }
        });

        // add window dragging
        node.setOnMousePressed((MouseEvent event) -> {
            mouseDragOffsetX = event.getSceneX();
            mouseDragOffsetY = event.getSceneY();
        });
        node.setOnMouseDragged((MouseEvent event) -> {
            if (!maximized) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setX(event.getScreenX() - mouseDragOffsetX);
                stage.setY(event.getScreenY() - mouseDragOffsetY);
            }
        });
    }

    /**
     * Handle action related to "About" menu item.
     *
     * @param event Event on "About" menu item.
     */
    @FXML
    private void handleAboutAction(final ActionEvent event) {
        provideAboutFunctionality();
    }

    /**
     * Handle action related to input (in this case specifically only responds to keyboard event CTRL-A).
     *
     * @param event Input event.
     */
    @FXML
    private void handleKeyInput(final InputEvent event) {
        if (event instanceof KeyEvent) {
            final KeyEvent keyEvent = (KeyEvent) event;
            if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.A) {
                provideAboutFunctionality();
            }
        }
    }

    /**
     * Perform functionality associated with "About" menu selection or CTRL-A.
     */
    private void provideAboutFunctionality() {
        System.out.println("You clicked on About!");
    }

    private SwingNode createSwingContent() {
        
        final SwingNode swingNode = new SwingNode();
        
        SwingUtilities.invokeLater(() -> {

            final mxGraphComponent graphComponent = new mxGraphComponent(new mxGraph());

            mxSwingConstants.SHADOW_COLOR = Color.DARK_GRAY;
            mxConstants.W3C_SHADOWCOLOR = "#262626";

            // Sets switches typically used in an editor
            graphComponent.setPageVisible(true);
            graphComponent.setGridVisible(false);
            graphComponent.setToolTips(true);

            graphComponent.setColumnHeaderView(new MyEditorRuler(graphComponent, MyEditorRuler.ORIENTATION_HORIZONTAL));
            graphComponent.setRowHeaderView(new MyEditorRuler(graphComponent, MyEditorRuler.ORIENTATION_VERTICAL));

            graphComponent.getVerticalScrollBar().setUI(new MyScrollBarUI());
            graphComponent.getHorizontalScrollBar().setUI(new MyScrollBarUI());

            // TODO: Loads the defalt stylesheet from an external file
            // Sets the background to white
            graphComponent.getViewport().setOpaque(true);
            graphComponent.getViewport().setBackground(Color.WHITE);

            graphComponent.setBackground(Color.DARK_GRAY);
            graphComponent.setPageBackgroundColor(Color.DARK_GRAY);
//            graphComponent.setPageShadowColor(Color.DARK_GRAY);
            graphComponent.setPageShadowColor(new Color(54, 54, 54));
            graphComponent.setPageBorderColor(Color.DARK_GRAY);
            graphComponent.setPageBreakColor(Color.DARK_GRAY);

            swingNode.setContent(graphComponent);
        });
        
        return swingNode;
    }
}
