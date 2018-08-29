package vaucangraph.PainelPropriedade;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.layout.AnchorPane;

public class PainelPropriedadeView implements FxmlView<PainelPropriedadeViewModel>, Initializable {

    @InjectViewModel
    private PainelPropriedadeViewModel viewModel;

    @FXML
    private TabPane painelPropriedade;
    @FXML
    private Tab abaEstilo;
    @FXML
    private Tab abaPropriedade;
    @FXML
    private TableView tablePropriedade;
    @FXML
    private TableColumn tableColunaPropriedade;
    @FXML
    private TableColumn tableColunaValor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        AnchorPane.setTopAnchor(painelPropriedade, 0.0);
        AnchorPane.setBottomAnchor(painelPropriedade, 0.0);
        AnchorPane.setLeftAnchor(painelPropriedade, 0.0);
        AnchorPane.setRightAnchor(painelPropriedade, 0.0);
    }
}
