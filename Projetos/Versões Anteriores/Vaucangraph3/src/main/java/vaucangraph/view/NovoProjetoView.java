package vaucangraph.view;

import com.jfoenix.controls.JFXTabPane;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import vaucangraph.BarraMenu.BarraMenuView;
import vaucangraph.viewModel.NovoProjetoViewModel;
import vaucangraph.viewModel.VaucangraphViewModel;

public class NovoProjetoView implements FxmlView<VaucangraphViewModel>, Initializable {

    @FXML
    private TabPane tabPaneProjeto;

    @InjectViewModel
    private NovoProjetoViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
       
    }
    
    
}
