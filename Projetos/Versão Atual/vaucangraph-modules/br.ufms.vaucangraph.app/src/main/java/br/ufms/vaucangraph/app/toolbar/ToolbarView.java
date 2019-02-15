package br.ufms.vaucangraph.app.toolbar;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class ToolbarView implements FxmlView<ToolbarViewModel>, Initializable {
    @FXML
    private TextField width;
    @FXML
    private TextField height;
    @FXML
    private ComboBox zoomComboBox;
    @InjectViewModel
    private ToolbarViewModel viewModel;
    private StringProperty testeS = new SimpleStringProperty();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NumberStringConverter nsc = new NumberStringConverter();
        Bindings.bindBidirectional(width.textProperty(), viewModel.widthProperty(), nsc);
        Bindings.bindBidirectional(height.textProperty(), viewModel.heightProperty(), nsc);
        zoomComboBox.setItems(comboBox());



    }

    @FXML
    public ObservableList<String> comboBox() {
        ObservableList<String> zoomList = FXCollections.observableArrayList();
        zoomList.addAll("400%", "200%", "150%", "100%", "75%",
                "50%", "Fit window", "Fit page", "Fit page width", "Two pages");
        return  zoomList;
    }

//    @FXML
//    public void addNewContact() {
//        ViewTuple<FileChooserView, FileChooserViewModel> load = FluentViewLoader
//                .fxmlView(FileChooserView.class)
//                .providedScopes(viewModel.())
//                .load();
//        Parent view = load.getView();
//        Stage showDialog = DialogHelper.showDialog(view, primaryStage, "/contacts.css");
//        load.getCodeBehind().setDisplayingStage(showDialog);
//    }
}
