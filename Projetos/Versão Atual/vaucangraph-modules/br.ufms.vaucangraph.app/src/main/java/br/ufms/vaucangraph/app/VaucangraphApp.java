package br.ufms.vaucangraph.app;

import br.ufms.vaucangraph.app.main.MainView;
import br.ufms.vaucangraph.app.main.MainViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class VaucangraphApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        final ViewTuple<MainView, MainViewModel> viewTuple = FluentViewLoader.fxmlView(MainView.class).load();
        Scene scene = new Scene(viewTuple.getView());

        scene.getStylesheets().add(getClass().getResource(
                "/css/vaucangraph-default.css").toExternalForm());
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
