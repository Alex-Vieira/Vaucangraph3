package vaucangraph.PainelModelagem;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import java.awt.event.MouseListener;

import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import vaucangraph.model.Vertice;

public class PainelModelagemView implements FxmlView<PainelModelagemViewModel>, Initializable {

    @FXML
    private AnchorPane anchorPaneModelagem;

    @FXML
    private WebView webView;

    @InjectViewModel
    private PainelModelagemViewModel viewModel;
    double heigth, width;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        heigth = 200;
        width = 200;
        
        WebEngine webEngine = webView.getEngine();
        URL url = this.getClass().getResource("/html/Vaucangraph.html");
        webEngine.load(url.toString());

     

        System.out.println("Width: " + webView.getWidth());
        System.out.println("Height: " + webView.getHeight());
        
        webView.setOnMouseClicked((MouseEvent event) -> {
            anchorPaneModelagem.setPrefSize(heigth, width);
            webEngine.executeScript("tamanhoPainel('" + webView + "','" + webView + "')");
            System.out.println("Width click: " + webView.getWidth());
            System.out.println("Height click: " + webView.getHeight());
            
            Vertice v = new Vertice(null, "aa", 40, 40);

            
            webEngine.executeScript("adicionaVertice('" + event.getX() + "','" + event.getY() +"','" + v.getId()+ "','" +v.getNome()+"','" +v.getTamanhoX()+"','" +v.getTamanhoY()+"','" +"shape=ellipse;perimter=ellipsePerimeter"+ "')");
            

        });

    }

    public void teste() {

    }
}
