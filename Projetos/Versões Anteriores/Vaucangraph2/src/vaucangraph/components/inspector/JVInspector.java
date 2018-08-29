package vaucangraph.components.inspector;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import jgraph.model.vGraphCell;
import jgraph.model.vMutableTreeNode;
import jgraph.view.Canvas;
import vaucansong.bean.State;
import vaucansong.bean.Transition;

/**
 * JTree Inspector - inspetor de elementos Vaucangraph
 * @author Kleber Kruger
 */
public class JVInspector extends JTree {

    private final Canvas canvas;
    private DefaultMutableTreeNode root;

    /**
     * Classe que controla o inspector (JTree)
     *
     * @param canvas
     * O canvas que contém os componentes do autômato ou grafo
     */
    public JVInspector(Canvas canvas) {
        super();
        this.canvas = canvas;

        // Contrói a arvore
        initComponents();
    }

    /**
     * Método que constrói a árvore, adicionando listeners e renderizando as
     * células da JTree
     */
    private void initComponents() {

        // Constrói o nó root de acordo com o nome do projeto
        if (canvas != null) {
            // Define o nome do inspector de acordo com o nome do projeto
            root = new DefaultMutableTreeNode(canvas.getProject().getName());
        } else {
            // Define o nome do inspector para Vaucangraph (nome padrão)
            root = new DefaultMutableTreeNode("Vaucangraph");
        }

        // Define o modelo de acordo com o nó root
        DefaultTreeModel model = (DefaultTreeModel) getModel();
        model.setRoot(root);

        setRowHeight(20); // Define o tamanho da altura de cada nó
        setEditable(true); // Define os nós para editáveis
        setToolTipText("<html>\n<b>Inspetor de elementos</b><br>\n" +
                "Selecione um componente através do inspetor\n</html>");

        addMouseListener();
        setCellRenderer(new InspectorCellRenderer(this));
    }

    /**
     * Atualiza todos os nós
     */
    public void refresh() {
        DefaultTreeModel model = (DefaultTreeModel) getModel();
        model.reload();
        expandAll(true);
    }

    /**
     * Atualiza o nó recebido por parâmetro
     * @param node
     */
    public void refreshNode(TreeNode node) {
        DefaultTreeModel model = (DefaultTreeModel) getModel();
        model.reload(node);
        for (int i = 0; i < node.getChildCount(); i++) {
            model.reload(node.getChildAt(i));
        }
    }

    /**
     * Método utilitário que adiciona um estado na JTree
     * @param state
     */
    public void insertState(State state) {
        DefaultTreeModel model = (DefaultTreeModel) getModel();

        state.setNode(null);
        model.insertNodeInto(state.getInspectorNode(), root, root.getChildCount());
        showLastNodeAdded(state.getInspectorNode());
    }

    /**
     * Método utilitário que adiciona uma transição na JTree
     * @param transition
     */
    public void insertTransition(Transition transition) {
        DefaultTreeModel model = (DefaultTreeModel) getModel();
        vMutableTreeNode selectedNode = transition.getSourceState().getInspectorNode();

        model.insertNodeInto(transition.getInspectorNode(), selectedNode,
                selectedNode.getChildCount());

        showLastNodeAdded(transition.getInspectorNode());
    }

    /**
     *
     * @param state
     */
    public void removeState(State state) {
        DefaultTreeModel model = (DefaultTreeModel) getModel();
        model.removeNodeFromParent(state.getInspectorNode());
    }

    /**
     * 
     * @param transition
     */
    public void removeTransition(Transition transition) {
        DefaultTreeModel model = (DefaultTreeModel) getModel();
        model.removeNodeFromParent(transition.getInspectorNode());
    }

    private void showLastNodeAdded(MutableTreeNode lastNode) {
        DefaultTreeModel model = (DefaultTreeModel) getModel();
        TreeNode[] nodes = model.getPathToRoot(lastNode);
        TreePath path = new TreePath(nodes);
        scrollPathToVisible(path);
    }

    public void expandAll(boolean expand) {
        root = (DefaultMutableTreeNode) getModel().getRoot();
        // Traverse tree from root
        expandAll(new TreePath(root), expand);
    }

    private void expandAll(TreePath parent, boolean expand) {
        // Traverse children
        TreeNode node = (TreeNode) parent.getLastPathComponent();
        if (node.getChildCount() >= 0) {
            for (Enumeration e = node.children(); e.hasMoreElements();) {
                TreeNode n = (TreeNode) e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                expandAll(path, expand);
            }
        }
        // Expansion or collapse must be done bottom-up
        if (expand) {
            expandPath(parent);
        } else {
            collapsePath(parent);
        }
    }

    /**
     * Adiciona eventos no inspector
     * @return os eventos
     */
    private void addMouseListener() {

        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                if (getLastSelectedPathComponent() != null) {
                    if (getLastSelectedPathComponent() instanceof vMutableTreeNode) {

                        vMutableTreeNode node = (vMutableTreeNode) getLastSelectedPathComponent();
                        vGraphCell cell = node.getCell();

                        if (cell != null) {
                            // Seleciona o estado correspondente no canvas.
                            canvas.setSelectionCell(cell);
                        }
                    }
                }

            } // Fim
        });

    }

    public void reload() {
        // Remove todos os elementos do inspector
        removeAllNodes();

        for (State s : canvas.getProject().getStates()) {
            insertState(s);
        }
        for (Transition t : canvas.getProject().getTransitions()) {
            insertTransition(t);
        }
    }

    public void removeAllNodes() {

        while (root.getChildCount() > 0) {
            DefaultTreeModel model = (DefaultTreeModel) getModel();
            model.removeNodeFromParent((MutableTreeNode) root.getChildAt(0));
        }
    }

    /**
     * @return o canvas
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * @return o nó root que contém o nome do projeto
     */
    public DefaultMutableTreeNode getRoot() {
        return root;
    }
}
