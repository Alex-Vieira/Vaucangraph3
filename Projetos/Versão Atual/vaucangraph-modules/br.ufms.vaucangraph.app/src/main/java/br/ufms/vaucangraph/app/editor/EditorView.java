package br.ufms.vaucangraph.app.editor;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class EditorView implements FxmlView<EditorViewModel>, Initializable {
    @FXML
    private TabPane projectTabPane;


    @InjectViewModel
    private EditorViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newProject("1");
        newProject("2");
    }

    public void newProject(String name){
        Tab tab = new Tab(name);
        Pane pane = new Pane();


        projectTabPane.getTabs().add(tab);

    }

    public void Transducer(){

//        \begin{VCPicture}{( , )( , ){

//        \end{VCPicture}
    }
}
