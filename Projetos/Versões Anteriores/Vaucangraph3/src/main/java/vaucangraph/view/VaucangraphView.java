package vaucangraph.view;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vaucangraph.BarraFerramentasSuperior.BarraFerramentasSuperiorView;
import vaucangraph.BarraMenu.BarraMenuView;
import vaucangraph.PainelFuncionalidade.PainelFuncionalidadeView;
import vaucangraph.PainelModelagem.PainelModelagemView;
import vaucangraph.PainelPropriedade.PainelPropriedadeView;
import vaucangraph.viewModel.VaucangraphViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ToolBar;
import vaucangraph.BarraFerramentasLateral.BarraFerramentasLateralView;
import vaucangraph.BarraTipoModelagem.BarraTipoModelagemView;
import vaucangraph.ControleDeJanela.ControleDeJanelaView;

public class VaucangraphView implements FxmlView<VaucangraphViewModel>, Initializable {

    @FXML
    private AnchorPane anchorPanePrincipal;

    @FXML
    private VBox VBoxPrincipal;
    @FXML
    private HBox HboxMid;
    @FXML
    private HBox HBoxMenu;
    @FXML
    private AnchorPane anchorPaneModelagem;
    @FXML
    private AnchorPane anchorPaneFuncionalidade;
    @FXML
    private AnchorPane anchorPanePropriedade;
    @FXML
    private SplitPane splitPaneMid;
    @FXML
    private SplitPane splitPanePaineis;
    @FXML
    private MenuBar menuBarMenu;
    @FXML
    private ToolBar toolBarLateral;
    @FXML
    private ToolBar toolBarSuperior;
    @FXML
    private ToolBar toolBarJanela;
    @FXML
    private ToolBar toolBarTipoModelagem;

    @InjectViewModel
    private VaucangraphViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnchorPane.setBottomAnchor(anchorPanePrincipal, 0.0);
        AnchorPane.setTopAnchor(anchorPanePrincipal, 0.0);
        AnchorPane.setLeftAnchor(anchorPanePrincipal, 0.0);
        AnchorPane.setRightAnchor(anchorPanePrincipal, 0.0);
        


        try {
           
            HBoxMenu.getChildren().add(FXMLLoader.load(BarraMenuView.class.getResource("/fxml/BarraMenuView.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(VaucangraphView.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            toolBarJanela.getItems().add(FXMLLoader.load(ControleDeJanelaView.class.getResource("/fxml/ControleDeJanelaView.fxml")));

        } catch (IOException ex) {
            Logger.getLogger(VaucangraphView.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            toolBarSuperior.getItems().add(FXMLLoader.load(BarraFerramentasSuperiorView.class.getResource("/fxml/BarraFerramentasSuperiorView.fxml")));

        } catch (IOException ex) {
            Logger.getLogger(VaucangraphView.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            anchorPaneFuncionalidade.getChildren().add(FXMLLoader.load(PainelFuncionalidadeView.class.getResource("/fxml/PainelFuncionalidadeView.fxml")));

        } catch (IOException ex) {
            Logger.getLogger(VaucangraphView.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            anchorPanePropriedade.getChildren().add(FXMLLoader.load(PainelPropriedadeView.class.getResource("/fxml/PainelPropriedadeView.fxml")));

        } catch (IOException ex) {
            Logger.getLogger(VaucangraphView.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            anchorPaneModelagem.getChildren().add(FXMLLoader.load(PainelModelagemView.class.getResource("/fxml/PainelModelagemView.fxml")));

        } catch (IOException ex) {
            Logger.getLogger(VaucangraphView.class.getName()).log(Level.SEVERE, null, ex);
        }

        // splitPaneMid.getItems().add(splitPanePaineis);
        try {

            toolBarLateral.getItems().add(FXMLLoader.load(BarraFerramentasLateralView.class.getResource("/fxml/BarraFerramentasLateralView.fxml")));

        } catch (IOException ex) {
            Logger.getLogger(VaucangraphView.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            toolBarTipoModelagem.getItems().add(FXMLLoader.load(BarraTipoModelagemView.class.getResource("/fxml/BarraTipoModelagemView.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(VaucangraphView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
