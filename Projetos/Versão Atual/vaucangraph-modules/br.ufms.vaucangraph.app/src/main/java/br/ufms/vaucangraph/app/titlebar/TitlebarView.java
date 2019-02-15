package br.ufms.vaucangraph.app.titlebar;

import de.saxsys.mvvmfx.FxmlView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class TitlebarView implements FxmlView<TitlebarViewModel>, Initializable {
    @FXML
    private Button btnClose;
    @FXML
    private Button btnMaximize;
    @FXML
    private Button btnMinimize;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

      btnClose.setOnMouseClicked(event -> System.exit(0));

    }
}
