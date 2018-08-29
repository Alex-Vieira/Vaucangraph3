package vaucangraph.BarraMenu;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.layout.AnchorPane;

public class BarraMenuView implements FxmlView<BarraMenuViewModel>, Initializable  {
    @FXML
    private MenuBar menuBar;
    @FXML
    public Menu menuFile;
    @FXML
    private Menu menuEdit;
    @FXML
    private Menu menuView;
    @FXML
    private Menu menuHelp;
    @FXML
    private Menu menuExport;
    @FXML
    private MenuItem menuItemAbout;


    @InjectViewModel
    private BarraMenuViewModel viewModel;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnchorPane.setTopAnchor(menuBar, 0.0);
        AnchorPane.setBottomAnchor(menuBar, 0.0);
        AnchorPane.setLeftAnchor(menuBar, 0.0);
        AnchorPane.setRightAnchor(menuBar, 0.0);
    }

    
    
}
