package jgraph.view;

import interpreter.Interpreter;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import java.util.ArrayList;

import javax.swing.TransferHandler;

import jgraph.controller.vMarqueeHandler;
import jgraph.controller.vUndoManager;
import jgraph.model.vCellViewFactory;
import jgraph.model.vGraphCell;
import jgraph.model.vGraphModel;

import org.jgraph.JGraph;
import org.jgraph.graph.GraphLayoutCache;

import vaucangraph.components.inspector.JVInspector;
import vaucangraph.components.propertytable.JVPropertyTable;
import vaucangraph.components.statusbar.JVStatusBar;
import vaucangraph.model.Project;
import vaucangraph.model.enumeration.ProjectType;
import vaucangraph.view.ProjectPanel;
import vaucansong.bean.State;
import vaucansong.bean.Transition;

/**
 * Canvas - componente que representa a área de desenho da ferramenta
 *
 * <b>Vaucangraph 2</b> - A evolução de uma ferramenta para desenho de autômatos
 * e grafos com geração de códigos Vaucanson-G <br><br>
 *
 * Desenvolvida por: <br><br>
 * Kleber Kruger <br>
 * José Ademar Peixoto de Souza <br>
 *
 * @version 2.0
 * @since 2010-05-15
 *
 * @author Kleber Kruger
 * @author José Ademar Peixoto de Souza
 */
public class Canvas extends JGraph {

    /**
     * Componentes associados ao canvas
     */
    protected final ProjectPanel panel;
    protected final JVInspector inspector;
    protected final JVPropertyTable propertyTable;
    protected final JVStatusBar statusBar;
    /**
     * Atributo que contém as informações do projeto 
     */
    private final Project project;
    /**
     * Tipos de objetos personalizados, herdados da biblioteca jgraph
     */
    protected vUndoManager undoManager;
    protected State cellCache;

    public Canvas(ProjectPanel panel) {
        // Cria um novo canvas com um modelo personalizado
        super(new vGraphModel(panel), new GraphLayoutCache());

        this.panel = panel;
        this.project = panel.getProject();

        // Cria o inspector, a tabela de propriedades, e a barra de status
        inspector = new JVInspector(this);
        propertyTable = new JVPropertyTable(this);
        statusBar = new JVStatusBar(this.project.getType());

        // Inicializa e configura os componentes do canvas de acordo com o projeto
        initComponents();

        // Adiciona as células (estados e transições) existentes no projeto
        insertCells(this.panel.getProject());

        // Cria e adiciona o gerenciador de desfazer/refazer
        addUndoRedoManager();
    }

    /**
     * Método que configura as propriedades do canvas
     * @param project
     */
    private void initComponents() {

        // Propriedades do JGraph (Painel de Visualização) //
        setName(project.getName()); // Define o nome do canvas
        setSize(project.getSize()); // Define o tamanho do canvas
        setBackground(Color.white); // Define a cor de fundo

        setBorder(javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(0, 0, 0), 1)); // Define a borda

        setGridEnabled(false); // Define o grid como desabilitado
        setGridVisible(false); // Define o grid como invisível
        setGridMode(JGraph.LINE_GRID_MODE); // Define o modelo do grid para "em linhas"
        setGridColor(Color.lightGray); // Define a cor do grid

        setHandleColor(Color.blue.brighter()); // Define a cor do selecionador
        setHighlightColor(Color.white.brighter());

        setMoveBeyondGraphBounds(false); // Não permite que as células se movimentem alem dos limites do canvas
        setAntiAliased(true); // Ativa o anti-alias

        setPortsOnTop(false); // False para a port view não ficar visível sobre a célula
        setPortsVisible(false);

        setMarqueeHandler(new vMarqueeHandler(this));
        getGraphLayoutCache().setFactory(new vCellViewFactory());
    }

    @Override
    public void setSize(Dimension d) {
        setSize((int) d.getWidth(), (int) d.getHeight());
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);

        panel.getJpDesign().setPreferredSize(new Dimension(width + 20, height + 20));
        setLocation(10, 10);
        panel.getJspDesign().setViewportView(panel.getJpDesign());
    }

    @Override
    public void setScale(double newValue) {
        this.setScale(newValue, getCenterPoint());
    }

    @Override
    public void setScale(double newValue, Point2D center) {
        if (newValue >= 0.1) {
            super.setScale(newValue, center);
            setSize((int) (project.getSize().getWidth() * newValue),
                    (int) (project.getSize().getHeight() * newValue));

            // Bloqueia
            if (getScale() < 0.2) {
                panel.getVaucangraph().getJbZoomOut().setEnabled(false);
            } else {
                panel.getVaucangraph().getJbZoomOut().setEnabled(true);
            }
        }
    }

    @Override
    public void refresh() {
        super.refresh();

        project.setVaucanson(Interpreter.getCode(this) + "\n");
        panel.getJepCode().setText(project.getVaucanson());
    }

    @Override
    public BufferedImage getImage(Color bg, int inset) {
        super.getImage(bg, inset);
        // TODO, this method could just use the offscreen if available
        setBorder(null);
        repaint();

        Rectangle2D bounds = getBounds();
        BufferedImage img = null;

        if (bounds != null) {
            toScreen(bounds);
            GraphicsConfiguration graphicsConfig = getGraphicsConfiguration();
            if (graphicsConfig != null) {
                img = getGraphicsConfiguration().createCompatibleImage(
                        (int) bounds.getWidth() + 2 * inset,
                        (int) bounds.getHeight() + 2 * inset,
                        (bg != null) ? Transparency.OPAQUE
                        : Transparency.BITMASK);
            } else {
                img = new BufferedImage((int) bounds.getWidth() + 2 * inset,
                        (int) bounds.getHeight() + 2 * inset,
                        (bg != null) ? BufferedImage.TYPE_INT_RGB
                        : BufferedImage.TYPE_INT_ARGB);
            }

            Graphics2D graphics = img.createGraphics();
            if (bg != null) {
                graphics.setColor(bg);
                graphics.fillRect(0, 0, img.getWidth(), img.getHeight());
            } else {
                graphics.setComposite(AlphaComposite.getInstance(
                        AlphaComposite.CLEAR, 0.0f));
                graphics.fillRect(0, 0, img.getWidth(), img.getHeight());
                graphics.setComposite(AlphaComposite.SrcOver);
            }
            graphics.translate((int) (-bounds.getX() + inset), (int) (-bounds.getY() + inset));
            print(graphics);
            graphics.dispose();
        }
        // Redefine a borda
        setBorder(javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(0, 0, 0), 1)); // Define a borda

        return img;
    }

    @Override
    public void setSelectionCell(Object cell) {
        super.setSelectionCell(cell);
        // Seleciona a célula selecionada no inspector
        if (cell instanceof State || cell instanceof Transition) {
            vGraphCell vCell = (vGraphCell) cell;
            inspector.setSelectionPath(vCell.getInspectorNode().getNodePath());
            // Atualiza o nó do inspector
            inspector.refreshNode(vCell.getInspectorNode());
        }
    }

    @Override
    public void setSelectionCells(Object[] cells) {
        super.setSelectionCells(cells);

        for (Object cell : cells) {
            // Seleciona as células selecionadas no inspector
            if (cell instanceof State || cell instanceof Transition) {
                vGraphCell vCell = (vGraphCell) cell;
                inspector.setSelectionPath(vCell.getInspectorNode().getNodePath());
                // Atualiza o nó do inspector
                inspector.refreshNode(vCell.getInspectorNode());
            }
        }
    }

    public MouseEvent getRealMouseEvent(MouseEvent evt) {
        Point2D p = getRealPosition(evt.getPoint());
        return new MouseEvent(this, evt.getID(), evt.getWhen(), evt.getModifiers(),
                (int) p.getX(), (int) p.getY(), evt.getClickCount(), evt.isPopupTrigger());
    }

    public Point2D getRealPosition(Point2D p) {
        return new Point2D.Double(p.getX() / getScale(), p.getY() / getScale());
    }

    /**
     * Método que insere as células (estados e transições) de um projeto
     * no canvas
     * @param project o objeto que contém as informações do projeto
     */
    private void insertCells(Project project) {
        if (project != null) {
            for (State s : project.getStates()) {
                getGraphLayoutCache().insert(s);
            }
            for (Transition t : project.getTransitions()) {
                getGraphLayoutCache().insertEdge(t,
                        t.getSourceState().getChildAt(0),
                        t.getTargetState().getChildAt(0));
            }
            reloadInspector();
        }

        getGraphLayoutCache().reload();
        refresh();

        // Expande todos os nós do inspector
        inspector.expandAll(true);
    }

    /**
     * Método que adiciona várias células ao canvas
     * @param cells
     */
    public void insertCells(vGraphCell[] cells) {
        for (vGraphCell cell : cells) {
            insertCell(cell);
        }
    }

    /**
     * Método que adiciona uma célula vaucangraph (vGraphCell - State, Transition)
     * no canvas
     * @param cell a célula vaucangraph (vGraphCell - State, Transition)
     */
    public void insertCell(vGraphCell cell) {
        if (cell instanceof State) {
            // Obtém o estado e o insere no canvas
            State state = (State) cell;
            getGraphLayoutCache().insert(cell);
            // Adiciona o estado no ArrayList
            project.getStates().add(state);
            // Adiciona o estado ao inspector
            inspector.insertState(state);
        }
        if (cell instanceof Transition) {
            // Obtém a transição e a insere no canvas
            Transition transition = (Transition) cell;
            getGraphLayoutCache().insertEdge(transition,
                    transition.getSourceState().getChildAt(0),
                    transition.getTargetState().getChildAt(0));
            // Adiciona a transição no ArrayList
            project.getTransitions().add(transition);
            // Adiciona a transição no array de transições do estado de origem
            transition.getSourceState().getTransitions().add(transition);
            // Adiciona a transição ao inspector
            inspector.insertTransition(transition);
        }
        refresh();
    }

    public void removeCells(Object[] cells) {

        if (cells != null && cells.length > 0) {

            for (Object obj : cells) {
                // Remove estados
                if (obj instanceof State) {
                    State state = (State) obj;
                    // Remove recursivamente todas as transições do estado deletado
                    removeCells(state.getTransitions().toArray());
                    inspector.removeState(state);
                    project.getStates().remove(state);
                }
                // Remove transições
                if (obj instanceof Transition) {
                    Transition transition = (Transition) obj;
                    //inspector.removeTransition(transition);
                    project.getTransitions().remove(transition);
                }
            }
            // Remove todas as células recebidas
            getGraphLayoutCache().remove(cells);
        }
        propertyTable.insertData(null);
        refresh();
    }

    /**
     * Cria e adiciona ao canvas o gerenciador de ações desfazer/refazer
     */
    private void addUndoRedoManager() {
        undoManager = new vUndoManager(this);
        getModel().addUndoableEditListener(undoManager);
    }

    /**
     * Desfaz a ultima ação realizada no canvas
     */
    public void undo() {
        try {
            undoManager.undo(getGraphLayoutCache());
            reloadInspector();
        } finally {
            undoManager.updateUndoRedo();
        }
    }

    /**
     * Refaz a ultima ação realizada no canvas
     */
    public void redo() {
        try {
            undoManager.redo(getGraphLayoutCache());
            reloadInspector();
        } finally {
            undoManager.updateUndoRedo();
        }
    }

    public void zoomOut() {
        setScale(getScale() - 0.1);
        processZoom();
    }

    public void zoomIn() {
        setScale(getScale() + 0.1);
        processZoom();
    }

    public void processZoom() {
        setSize((int) (project.getSize().getWidth() * getScale()),
                (int) (project.getSize().getHeight() * getScale()));
    }

    public void cut(ActionEvent evt) {
        ActionEvent newEvent = new ActionEvent(this, evt.getID(), evt.getActionCommand());
        TransferHandler.getCutAction().actionPerformed(newEvent);
        reloadInspector();
    }

    public void copy(ActionEvent evt) {
        ActionEvent newEvent = new ActionEvent(this, evt.getID(), evt.getActionCommand());
        TransferHandler.getCopyAction().actionPerformed(newEvent);
        reloadInspector();
    }

    public void paste(ActionEvent evt) {
        ActionEvent newEvent = new ActionEvent(this, evt.getID(), evt.getActionCommand());
        TransferHandler.getPasteAction().actionPerformed(newEvent);
        reloadInspector();
    }

    public void reloadCells() {

        getGraphLayoutCache().reload();

        ArrayList<State> states = new ArrayList<State>();
        ArrayList<Transition> transitions = new ArrayList<Transition>();

        int count = this.getGraphLayoutCache().getModel().getRootCount();
        Object[] elements = new Object[count];

        for (int i = 0; i < count; i++) {

            elements[i] = this.getGraphLayoutCache().getModel().getRootAt(i);

            if (elements[i] instanceof State) {
                states.add((State) elements[i]);
            }
            if (elements[i] instanceof Transition) {
                transitions.add((Transition) elements[i]);
            }
        }

        project.setStates(states);
        project.setTransitions(transitions);
    }

    /**
     * Atualiza o inspector, recarrega as células existentes e cria os nós
     */
    public void reloadInspector() {
        // Recarrega as células existentes
        reloadCells();
        if (inspector != null) {
            inspector.reload();
        }
    }

    /**
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * @return a generic id for state
     */
    public int getNextStateId() {
        int id = 0;
        while (!isValidStateId(id)) {
            id++;
        }
        return id;
    }

    private boolean isValidStateId(int id) {
        for (State s : project.getStates()) {
            if (s.getId() == id) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return a generic name for state
     */
    public String getGenericStateName() {
        if (project.getType() == ProjectType.GRAPH_DIRECTED
                || project.getType() == ProjectType.GRAPH_UNDIRECTED) {

            return "" + getNextStateId();
        }
        return "q" + getNextStateId();
    }

    /**
     * @return a generic name for transition
     */
    public String getGenericTransitionName() {

        String name = "";
        int id = 0;

        if (project.getType() == ProjectType.AUTOMATON) {
            name = "t";
        }

        while (!isValidTransitionName(name + id)) {
            id++;
        }

        return name + id;
    }

    private boolean isValidTransitionName(String name) {
        for (Transition t : project.getTransitions()) {
            if (t.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return the project panel
     */
    public ProjectPanel getProjectPanel() {
        return panel;
    }

    /**
     * @return the inspector
     */
    public JVInspector getInspector() {
        return inspector;
    }

    /**
     * @return the property table
     */
    public JVPropertyTable getPropertyTable() {
        return propertyTable;
    }

    /**
     * @return the status bar
     */
    public JVStatusBar getStatusBar() {
        return statusBar;
    }

    /**
     * @return the undo manager
     */
    public vUndoManager getUndoManager() {
        return undoManager;
    }
}
