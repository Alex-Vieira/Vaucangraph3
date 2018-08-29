package vaucangraph.BarraFerramentasSuperior;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;

public class BarraFerramentasSuperiorView implements FxmlView<BarraFerramentasSuperiorViewModel>, Initializable {

    @FXML
    private JFXTextField TFHorizontal;
    @FXML
    private JFXTextField TFVertical;
    @FXML
    private JFXButton BTNnovo;
    @FXML
    private JFXButton BTNabrir;
    @FXML
    private JFXButton BTNsalvar;
    @FXML
    private JFXButton BTNzoomMais;
    @FXML
    private JFXButton BTNzoomMenos;
    @FXML
    private JFXComboBox ComboZoom;
    @FXML
    private ToolBar toolBarPainelSuperior;
    @InjectViewModel
    private BarraFerramentasSuperiorViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        AnchorPane.setTopAnchor(toolBarPainelSuperior, 0.0);
        AnchorPane.setBottomAnchor(toolBarPainelSuperior, 0.0);
        AnchorPane.setLeftAnchor(toolBarPainelSuperior, 0.0);
        AnchorPane.setRightAnchor(toolBarPainelSuperior, 0.0);
  
     //ComboZoom.selectionModelProperty().bind(viewModel.ComboZoom());
       
    }
}