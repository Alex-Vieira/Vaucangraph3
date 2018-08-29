package jgraph.controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.SwingUtilities;

import jgraph.view.Canvas;

import org.jgraph.graph.BasicMarqueeHandler;
import org.jgraph.graph.CellView;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.Port;
import org.jgraph.graph.PortView;
import utils.math.MetricConversor;

import utils.state.StateUtils;

import vaucangraph.components.popupmenu.JVPopupMenu;
import vaucangraph.model.enumeration.ProjectType;
import vaucangraph.view.ProjectPanel;
import vaucansong.bean.State;
import vaucansong.bean.Transition;
import vaucansong.bean.enumeration.StateSize;
import vaucansong.bean.enumeration.TransitionType;

/**
 * Classe responsável pela maioria dos eventos no canvas
 * @author Kleber Kruger
 */
public class vMarqueeHandler extends BasicMarqueeHandler {

    protected final Canvas canvas;
    protected Point2D start, current;
    protected PortView firstPort, currentPort;
    protected State previewState, cellCache;

    public vMarqueeHandler(Canvas canvas) {
        this.canvas = canvas;
        addRemainingEvents();
    }

    /**
     * Adiciona os eventos restantes do canvas vaucangraph <br><br>
     * Eventos: MouseEntered, MouseExited, KeyPressed
     */
    private void addRemainingEvents() {

        final vMarqueeHandler marquee = this;
        // Adiciona eventos de mouse
        canvas.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent evt) {
                marquee.mouseEntered(evt);
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                marquee.mouseExited(evt);
            }

            @Override
            public void mousePressed(MouseEvent evt) {
                // Atualiza as informações na tabela de propriedades e na barra de status
                updatePropertyTable(canvas.getFirstCellForLocation(evt.getX(), evt.getY()));
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                // Atualiza as informações na tabela de propriedades e na barra de status
                updatePropertyTable(canvas.getFirstCellForLocation(evt.getX(), evt.getY()));
            }
        });
        // Adiciona eventos de teclado
        canvas.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent evt) {
                marquee.keyPressed(evt);
            }
        });

    }

    @Override
    public boolean isForceMarqueeEvent(MouseEvent e) {

        // Se a tecla Shift estiver pressionada
        if (e.isShiftDown()) {
            return false;
        }
        // Se o botão direito do mouse, mostra o menu popup
        if (SwingUtilities.isRightMouseButton(e)) {
            return true;
        }

        // Atualiza o valor de currentPort
        currentPort = getSourcePortAt(e.getPoint());

        // Se existir evento de pré-visualização
        if (previewState != null) {
            return true;
        }

        // Se a porta encontrada e o canvas estiver em processo de transição
        if (currentPort != null && canvas.isPortsVisible()) {
            return true;
        }

        return super.isForceMarqueeEvent(e);
    }

    public PortView getSourcePortAt(Point2D point) {
        canvas.setJumpToDefaultPort(true);
        PortView result;
        try {
            // Find a Port View in Model Coordinates and Remember
            result = canvas.getPortViewAt(point.getX(), point.getY());
        } finally {
            canvas.setJumpToDefaultPort(true);
        }
        return result;
    }

    public PortView getTargetPortAt(Point2D point) {
        // Find a Port View in Model Coordinates and Remember
        return canvas.getPortViewAt(point.getX(), point.getY());
    }

    /**
     * Deleta a(s) célula(s) selecionada(s) quando a tecla DELETE é pressionada
     * @param evt
     */
    public void keyPressed(KeyEvent evt) {
        // Tecla DELETE: Deletar a célula selecionada
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            if (!canvas.isSelectionEmpty()) {
                canvas.removeCells(canvas.getSelectionCells());
            }
        }
    }

    public void mouseEntered(MouseEvent evt) {

        // Painel do projeto
        ProjectPanel projectPanel = canvas.getProjectPanel();

        StateSize size = getStateSize(); // Tamanho do estado a ser inserido
        int id = canvas.getNextStateId(); // Código do estado
        String name = canvas.getGenericStateName();

        // Inserir estado inicial.
        if (projectPanel.getJtbInitialState().isSelected() && previewState == null) {

            Point2D point = getStartingPoint(true, size);
            previewState = new State(id, name, true, false, (int) point.getX(),
                    (int) point.getY(), size);
            canvas.getGraphLayoutCache().insert(previewState);

        } // Inserir estado simples.
        else if (projectPanel.getJtbSimpleState().isSelected()) {

            Point2D point = getStartingPoint(false, size);
            // Cria a célula de acordo com o tipo do projeto
            if (canvas.getProject().getType() == ProjectType.GRAPH_DIRECTED
                    || canvas.getProject().getType() == ProjectType.GRAPH_DIRECTED) {

                previewState = new State(id, name, (int) point.getX(), (int) point.getY(),
                        size, canvas.getProject().getType());
            } else {
                previewState = new State(id, name, false, false,
                        (int) point.getX(), (int) point.getY(), size);
            }
            canvas.getGraphLayoutCache().insert(previewState);

        } // Inserir estado final.
        else if (projectPanel.getJtbFinalState().isSelected()) {

            Point2D point = getStartingPoint(false, size);
            previewState = new State(id, name, false, true,
                    (int) point.getX(), (int) point.getY(), size);
            canvas.getGraphLayoutCache().insert(previewState);

        } // Inserir estado inicial e final.
        else if (projectPanel.getJtbInitialFinalState().isSelected()) {

            Point2D point = getStartingPoint(true, size);
            previewState = new State(id, name, true, true,
                    (int) point.getX(), (int) point.getY(), size);
            canvas.getGraphLayoutCache().insert(previewState);
        }
    }

    /**
     * @return o tamanho selecionado para criação do estado
     */
    private StateSize getStateSize() {
        ProjectPanel projectPanel = canvas.getProjectPanel();
        int selectedItem = projectPanel.getJcbStateSize().getSelectedIndex();
        StateSize size;

        switch (selectedItem) {
            case 0:
                size = StateSize.VERY_SMALL;
                break;
            case 1:
                size = StateSize.SMALL;
                break;
            case 2:
                size = StateSize.MEDIUM;
                break;
            case 3:
                size = StateSize.LARGE;
                break;
            default:
                size = StateSize.MEDIUM;
                break;
        }
        return size;
    }

    /**
     * Método que obtém a posição inicial do estado
     * @param initial
     * @param size
     * @return
     */
    private Point2D getStartingPoint(boolean initial, StateSize size) {

        // Obtém a largura e altura do estado
        Dimension2D d = StateUtils.getStateSize(initial, size);

        int widht = (int) d.getWidth();
        int height = (int) d.getHeight();

        int x = (int) (canvas.getMousePosition().getX() - (widht / 2)
                - StateUtils.getWidthArrow(initial, size));
        int y = (int) (canvas.getMousePosition().getY() - (height / 2));

        if (x < 0) {
            x = 0;
        }

        if (x > (canvas.getWidth() - widht)) {
            x = canvas.getWidth() - widht;
        }
        if (y < 0) {
            y = 0;
        }
        if (y > (canvas.getHeight() - height)) {
            y = canvas.getHeight() - height;
        }

        return new Point2D.Double(x, y);
    }

    /**
     * Classe que monitora os eventos de mouseExited dentro do canvas
     * @param e
     */
    public void mouseExited(MouseEvent e) {

        // Atualiza os pontos x,y do status
        canvas.getStatusBar().getJlMousePosition().setText("");

        // Se existir algum preview state, deleta o preview
        if (previewState != null) {
            removePreviewState();
        }
        // Reseta as variáveis globais
        firstPort = currentPort = null;
        start = current = null;
    }

    private void removePreviewState() {
        canvas.getGraphLayoutCache().remove(new Object[]{previewState});
        previewState = null;
    }

    @Override
    public void mousePressed(final MouseEvent e) {

        // Clique direito do mouse
        if (SwingUtilities.isRightMouseButton(e)) {
            // Cria menu popup
            new JVPopupMenu(canvas, canvas.getFirstCellForLocation(e.getX(),
                    e.getY())).show(canvas, e.getX(), e.getY());

        } // Insere self transition
        else if (canvas.getProjectPanel().getJtbSelfTransition().isSelected()
                && currentPort != null) {
            // Insere a self transition no estado selecionado
            Object cell = canvas.getFirstCellForLocation(e.getX(), e.getY());
            if (cell != null && cell instanceof State) {
                createSelfTransition((State) cell);
            }

        } // Senão, se existir porta e o canvas estiver liberado para transições
        else if (currentPort != null && canvas.isPortsVisible()) {
            // Guarda a localização da porta inicial na variável "start"
            start = canvas.toScreen(currentPort.getLocation());
            // Guarda a porta inicial na variável "firstPort"
            firstPort = currentPort;
        } else {
            if (previewState == null) {
                // Inicia as coordenadas da borda de seleção de componentes
                super.mousePressed(e);
            }
        }
    }

    public void createSelfTransition(State state) {
        Transition transition = new Transition(canvas.getGenericTransitionName(),
                state, state, TransitionType.SELF_TRANSITION);

        // Insere a célula no canvas
        canvas.insertCell(transition);
        canvas.setSelectionCell(state);
    }

    private void updateMousePosition(int x, int y) {
        canvas.getStatusBar().getJlMousePosition().setText(x + ", " + y + " px"
                + "   |   "
                + (int) MetricConversor.toCentimeters(x) + " x "
                + (int) MetricConversor.toCentimeters(y) + " cm");
    }

    /**
     * Método que deseleciona os botões de estados e transições no
     * painél de projeto
     */
    private void deselectButtons() {
        canvas.getProjectPanel().getButtonGroup().clearSelection();
    }

    /**
     * Método que adiciona as informações do objeto recebido por parâmetro 
     * na tabela de propriedades
     * 
     * @param selected o objeto selecionado
     */
    private void updatePropertyTable(Object selected) {

        // Insere informações da célula na tabela
        canvas.getPropertyTable().insertData(selected);

        String stateSizeInfo = "";

        // Obtém o tamanho do estado
        if (selected != null && selected instanceof State) {

            State state = (State) selected;
            Dimension2D size = StateUtils.getStateSize(state);

            stateSizeInfo = (int) size.getWidth() + " x " + (int) size.getHeight() + " px"
                    + "   |   " + state.getSize();
        }
        // Insere informação na barra de status
        canvas.getStatusBar().getJlStateSize().setText(stateSizeInfo);
    }

    // Cria a transição e repinta o canvas
    @Override
    public void mouseReleased(MouseEvent e) {
        //highlight(canvas, null);
        // Senão, se estiver selecionado a self transition
        if (e != null && previewState != null) {

            // Cria o novo estado
            State state = new State(previewState);

            // Define o novo estado como estado "real" e adiciona porta para transições
            state.setPreview(false);
            state.addPort();
            // Remove o estado de pré-visualização
            removePreviewState();

            // Insere o estado no canvas
            canvas.insertCell(state);
            updatePropertyTable(state);

            deselectButtons();
            canvas.setSelectionCell(state);

        }

        // If Valid Event, Current and First Port
        if (e != null && currentPort != null && firstPort != null
                && firstPort != currentPort) {
            // Cria a aresta/transição
            createTransition((Port) firstPort.getCell(), (Port) currentPort.getCell());
            e.consume();
            // Senão, repinta o canvas
        } else {
            canvas.repaint();
        }
        // Reseta as variáveis globais
        firstPort = currentPort = null;
        start = current = null;
        // Chama a superclasse
        super.mouseReleased(e);
    }

    // Insert a new Edge between source and target
    public void createTransition(Port source, Port target) {
        // Construct Edge with no label
        State sourceState = (State) canvas.getFirstCellForLocation(
                firstPort.getLocation().getX(), firstPort.getLocation().getY());
        State targetState = (State) canvas.getFirstCellForLocation(
                currentPort.getLocation().getX(), currentPort.getLocation().getY());

        System.out.println(source);
        System.out.println(target);

        String name = canvas.getGenericTransitionName();

        Transition edge = new Transition(name, sourceState, targetState, getTransitionType());

        if (canvas.getModel().acceptsSource(edge, source)
                && canvas.getModel().acceptsTarget(edge, target)) {

            canvas.insertCell(edge);
        }
    }

    /**
     * @return o tipo de transição de acordo com o projeto e botão selecionado
     */
    private TransitionType getTransitionType() {

        TransitionType type = null;

        // Projeto de autômato
        if (canvas.getProject().getType() == ProjectType.AUTOMATON) {

            if (canvas.getProjectPanel().getJtbSimpleTransition().isSelected()) {
                type = TransitionType.SIMPLE_TRANSITION;
            }

            if (canvas.getProjectPanel().getJtbSelfTransition().isSelected()) {
                type = TransitionType.SELF_TRANSITION;
            }

            if (canvas.getProjectPanel().getJtbArcTransition().isSelected()) {
                type = TransitionType.ARC_TRANSITION;
            }

        } else {

            if (canvas.getProject().getType() == ProjectType.GRAPH_DIRECTED) {
                type = TransitionType.EDGE_DIRECTED;
            }

            if (canvas.getProject().getType() == ProjectType.GRAPH_UNDIRECTED) {
                type = TransitionType.EDGE_UNDIRECTED;
            }
        }

        return type;
    }

    // Find Port under Mouse and Repaint Connector
    @Override
    public void mouseDragged(MouseEvent e) {

        if (e != null && previewState != null) {
            // Faz o estado se localizar onde o mouse está posicionado.
            setStateLocation(previewState, (int) (e.getX() / canvas.getScale()),
                    (int) (e.getY() / canvas.getScale()));

        } // If remembered Start Point is Valid
        else if (canvas.getProjectPanel().getJtbSimpleTransition().isSelected()
                || canvas.getProjectPanel().getJtbArcTransition().isSelected()
                && start != null) {

            // Fetch Graphics from Graph
            Graphics g = canvas.getGraphics();
            // Reset Remembered Port
            PortView newPort = getTargetPortAt(e.getPoint());
            // Do not flicker (repaint only on real changes)
            if (newPort == null || newPort != currentPort) {
                // Xor-Paint the old Connector (Hide old Connector)
                paintConnector(Color.black, canvas.getBackground(), g);
                // If Port was found then Point to Port Location
                currentPort = newPort;
                if (currentPort != null) {
                    current = canvas.toScreen(currentPort.getLocation());
                } // Else If no Port was found then Point to Mouse Location
                else {
                    current = canvas.snap(e.getPoint());
                }
                // Xor-Paint the new Connector
                paintConnector(canvas.getBackground(), Color.black, g);
            }
        }
        // Call Superclass
        super.mouseDragged(e);
    }

    // Show Special Cursor if Over Port
    @Override
    public void mouseMoved(MouseEvent e) {

        processMotion(e);

        if (e != null && previewState != null) {
            // Faz o estado se localizar onde o mouse está posicionado.
            setStateLocation(previewState, (int) (e.getX() / canvas.getScale()),
                    (int) (e.getY() / canvas.getScale()));
        }

        // Verifica se o canvas está habilitado para fazer transições e a porta encontrada
        if (e != null && getSourcePortAt(e.getPoint()) != null
                && canvas.isPortsVisible()) {
            // Altera o cursor
            canvas.setCursor(new Cursor(Cursor.HAND_CURSOR));
            // Consume evento
            // Observação: Este é o sinal de BasicGraphUI para parar o processamento de eventos
            e.consume();

        }
    }

    public void processMotion(MouseEvent e) {

        // Atualiza os pontos x,y do status
        updateMousePosition((int) canvas.getRealPosition(e.getPoint()).getX(),
                (int) canvas.getRealPosition(e.getPoint()).getY());

        Object currentCell = canvas.getFirstCellForLocation(e.getX(), e.getY());

        if (cellCache != null && cellCache != currentCell) {
            cellCache.setFocused(false);
            // Obtém a cellView da célula e renderiza
            canvas.getGraphLayoutCache().getMapping(cellCache, true).
                    getRendererComponent(canvas, false, false, false);
            // Atualiza o canvas
            canvas.refresh();
        }

        if (currentCell != null && currentCell instanceof State) {
            // Adiciona a célula em focus no cache
            cellCache = (State) currentCell;
            cellCache.setFocused(true);

            // Obtém a cellView da célula e renderiza
            canvas.getGraphLayoutCache().getMapping(cellCache, true).
                    getRendererComponent(canvas, false, true, false);
            // Atualiza o canvas
            canvas.refresh();
        }

        // Obtém a célula localizada no atual ponto x,y
        if (canvas.getFirstCellForLocation(e.getX(), e.getY()) != null) {
            canvas.setCursor(new Cursor(Cursor.MOVE_CURSOR));
        } else {
            canvas.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
        // Sinal de BasicGraphUI para processar evento
        e.consume();
    }

    private void setStateLocation(State state, int x, int y) {

        // Obtém as dimensões do estado
        Dimension2D d = StateUtils.getStateSize(state);

        int width = (int) d.getWidth();
        int height = (int) d.getHeight();

        // Centraliza o estado para o meio da setinha do mouse
        x = (int) StateUtils.getXToCentralize(state, x);
        y = (int) StateUtils.getYToCentralize(state, y);

        // Não permite que o estado saia do canvas.
        if (x < 0) {
            x = 0;
        }
        if (x > (canvas.getWidth() - width)) {
            x = canvas.getWidth() - width;
        }
        if (y < 0) {
            y = 0;
        }
        if (y > (canvas.getHeight() - height)) {
            y = canvas.getHeight() - height;
        }

        CellView cellView = canvas.getGraphLayoutCache().getMapping(state, true);

        // Cria o retângulo que determinará as propriedades:
        // Point (x,y) e Dimension (width,height)
        Rectangle2D rectangle = new Rectangle2D.Double(x, y,
                cellView.getBounds().getWidth(), cellView.getBounds().getHeight());
        // Define o novo lugar e tamanho da célula
        GraphConstants.setBounds(state.getAttributes(), rectangle);

        canvas.getGraphLayoutCache().reload();
        canvas.refresh();
    }

    protected void paintConnector(Color fg, Color bg, Graphics g) {

        if (canvas.isXorEnabled()) {
            // Set Foreground
            g.setColor(fg);
            // Set Xor-Mode Color
            g.setXORMode(bg);
            // Highlight the Current Port
            paintPort(canvas.getGraphics());

            drawConnectorLine(g);

        } else {

            Rectangle dirty = new Rectangle((int) start.getX(), (int) start.getY(), 1, 1);

            if (current != null) {
                dirty.add(current);
            }

            dirty.grow(1, 1);

            canvas.repaint(dirty);
            //highlight(canvas, currentPort);
        }
    }

    protected void paintPort(Graphics g) {
        // If Current Port is Valid
        if (currentPort != null) {
            // If Not Floating Port...
            boolean o = (GraphConstants.getOffset(currentPort.getAllAttributes()) != null);
            // ...Then use Parent's Bounds
            Rectangle2D rec = (o) ? currentPort.getBounds() : currentPort.getParentView().getBounds();
            // Scale from Model to Screen
            rec = canvas.toScreen((Rectangle2D) rec.clone());
            // Add Space For the Highlight Border
            rec.setFrame(rec.getX() - 3, rec.getY() - 3, rec.getWidth() + 6, rec.getHeight() + 6);
            // Paint Port in Preview (=Highlight) Mode
            canvas.getUI().paintCell(g, currentPort, rec, true);
        }
    }

    protected void drawConnectorLine(Graphics g) {
        if (firstPort != null && start != null && current != null) {
            // Then Draw A Line From Start to Current Point
            g.drawLine((int) start.getX(), (int) start.getY(),
                    (int) current.getX(), (int) current.getY());
        }
    }
}
