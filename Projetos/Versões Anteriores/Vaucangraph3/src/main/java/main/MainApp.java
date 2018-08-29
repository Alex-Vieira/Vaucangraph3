package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vaucangraph.view.VaucangraphView;

public class MainApp extends Application {

    public static void main(String... args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Vaucangraph 3.0");
        Scene scene = new Scene(FXMLLoader.load(VaucangraphView.class.getResource("/fxml/VaucangraphView.fxml")));
        scene.getStylesheets().add(getClass().getResource(
                "/style/mutavel.css").toExternalForm());
        stage.initStyle(StageStyle.DECORATED);  
        stage.setScene(scene);
     
        stage.show();
        
    }
}
