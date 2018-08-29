package vaucangraph.PainelVaucansonG;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class PainelVaucansonGView implements FxmlView<PainelVaucansonGViewModel>, Initializable {


    @InjectViewModel
    private PainelVaucansonGViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
