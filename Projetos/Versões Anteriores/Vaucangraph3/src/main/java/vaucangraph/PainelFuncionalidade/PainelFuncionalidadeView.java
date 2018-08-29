package vaucangraph.PainelFuncionalidade;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewModel;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class PainelFuncionalidadeView implements FxmlView<PainelFuncionalidadeViewModel>, Initializable {

    @InjectViewModel
    ViewModel viewModel;

    @FXML
    private TabPane painelFuncionalidade;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnchorPane.setTopAnchor(painelFuncionalidade, 0.0);
        AnchorPane.setBottomAnchor(painelFuncionalidade, 0.0);
        AnchorPane.setLeftAnchor(painelFuncionalidade, 0.0);
        AnchorPane.setRightAnchor(painelFuncionalidade, 0.0);

        /* treeView.setItems(viewModel.itemsProperty());

        viewModel.selectedItemProperty().bind(
                table.getSelectionModel().selectedItemProperty());
         */
    }

}
