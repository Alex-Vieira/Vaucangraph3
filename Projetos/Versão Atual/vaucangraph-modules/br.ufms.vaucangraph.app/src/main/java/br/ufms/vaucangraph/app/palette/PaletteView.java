package br.ufms.vaucangraph.app.palette;

import br.ufms.vaucangraph.core.GraphProperty;
import br.ufms.vaucangraph.core.ProjectProperty;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;

import java.net.URL;
import java.util.ResourceBundle;

public class PaletteView implements FxmlView<PaletteViewModel>, Initializable {
    @FXML
    private ToggleButton btnVertex;
    @InjectViewModel
    private PaletteViewModel viewModel;

    private GraphProperty graphProperty;
    private ProjectProperty projectProperty;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



}
