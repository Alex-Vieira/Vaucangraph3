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
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javax.swing.SwingUtilities;

/**
 * FXML Controller class
 *
 * @author kleberkruger
 */
public class ProjectPaneSwingController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        SwingNode swingNode = new SwingNode();
        
        createSwingContent(swingNode);

        anchorPane.getChildren().add(swingNode);
        
        AnchorPane.setTopAnchor(swingNode, 0.0);
        AnchorPane.setBottomAnchor(swingNode, 0.0);
        AnchorPane.setLeftAnchor(swingNode, 0.0);
        AnchorPane.setRightAnchor(swingNode, 0.0);
    }

    private void createSwingContent(final SwingNode swingNode) {
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
    }
}
