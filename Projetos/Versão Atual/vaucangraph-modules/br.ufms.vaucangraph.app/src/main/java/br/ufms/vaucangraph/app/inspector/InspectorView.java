package br.ufms.vaucangraph.app.inspector;

import br.ufms.vaucangraph.core.*;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.net.URL;
import java.util.ResourceBundle;

public class InspectorView implements FxmlView<InspectorViewModel>, Initializable {
    @FXML
    private TreeView treeInspector;
    @InjectViewModel
    private InspectorViewModel viewModel;

    private GraphProperty graphProperty;

    private TreeItem root = new TreeItem();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ProjectProperty projectProperty = new ProjectProperty("Teste", 200.0, 200.0, ProjectType.GRAPH);
        root.valueProperty().bindBidirectional(projectProperty.projectNameProperty());
        treeInspector.setRoot(root);
        root.setExpanded(true);

        graphProperty = projectProperty.getGraph();

            graphProperty.getVertexList().addListener((ListChangeListener<VertexProperty>) c -> {
                while (c.next()) {
                    if (c.wasAdded()) {
                        TreeItem item = new TreeItem(c.getList().get(c.getFrom()).getName());
                        root.getChildren().add(item);
                        c.getList().get(c.getFrom()).getAdjacencyMap().addListener((ListChangeListener.Change<? extends EdgeProperty> c1) -> {
                            while (c1.next()) {
                                if (c1.wasAdded()) {
                                    TreeItem child = new TreeItem(c1.getList().get(c1.getFrom()).getSource().getId() + " -> " + c1.getList().get(c1.getFrom()).getTarget().getId());
                                    item.getChildren().add(child);
                                } else if (c1.wasRemoved()) {
                                    System.out.println("deletar aresta");
                                }
                            }
                        });
                    }
                }
            });
    }
}
