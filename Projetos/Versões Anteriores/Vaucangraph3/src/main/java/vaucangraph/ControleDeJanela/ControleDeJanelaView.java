/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaucangraph.ControleDeJanela;

import de.saxsys.mvvmfx.FxmlView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author admin
 */
public class ControleDeJanelaView implements FxmlView<ControleDeJanelaViewModel>, Initializable {

    @FXML
    private ToolBar toolBarJanela;
    @FXML
    private AnchorPane anchorPaneJanela;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        anchorPaneJanela.setTopAnchor(toolBarJanela, 0.0);
        anchorPaneJanela.setBottomAnchor(toolBarJanela, 0.0);
        anchorPaneJanela.setLeftAnchor(toolBarJanela, 0.0);
        anchorPaneJanela.setRightAnchor(toolBarJanela, 0.0);
    }

}
