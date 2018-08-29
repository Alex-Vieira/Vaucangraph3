package vaucangraph.BarraFerramentasLateral;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;

public class BarraFerramentasLateralView implements FxmlView<BarraFerramentasLateralViewModel>, Initializable {

    @InjectViewModel
    private BarraFerramentasLateralViewModel viewModel;

    @FXML
    private ToolBar toolBarPainelLateral;

    @FXML
    JFXToggleButton TBSelecao;
    @FXML
    JFXToggleButton TBEstadoPequeno;
    @FXML
    JFXToggleButton TBEstadoMedio;
    @FXML
    JFXToggleButton TBEstadoGrande;
    @FXML
    JFXToggleButton A;
    @FXML
    JFXToggleButton B;
    @FXML
    JFXToggleButton C;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        AnchorPane.setTopAnchor(toolBarPainelLateral, 0.0);
        AnchorPane.setBottomAnchor(toolBarPainelLateral, 0.0);
        AnchorPane.setLeftAnchor(toolBarPainelLateral, 0.0);
        AnchorPane.setRightAnchor(toolBarPainelLateral, 0.0);

      //  setToggleGroup(TBSelecao, TBEstadoPequeno, TBEstadoMedio, TBEstadoGrande);
        
        //TBSelecao.selectedProperty().bindBidirectional(viewModel.getTBSelecao());
        //TBEstadoPequeno.selectedProperty().bindBidirectional(viewModel.getProducaoSelecionado());
        //TBEstadoMedio.selectedProperty().bindBidirectional(viewModel.getFinanceiroSelecionado());
        //TBEstadoGrande.selectedProperty().bindBidirectional(viewModel.getConfiguracaoSelecionado());



    }

}
