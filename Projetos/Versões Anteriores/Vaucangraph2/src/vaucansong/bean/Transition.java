package vaucansong.bean;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jgraph.model.vEdge;
import org.jgraph.graph.EdgeView;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphLayoutCache;
import utils.math.MathPoints;
import utils.state.StateUtils;
import utils.system.VariableType;
import vaucansong.bean.enumeration.TransitionType;

/**
 * Implementação de uma transição/aresta em um autômato ou grafo
 *
 * <br><br><br>
 * Vaucangraph 2 - Ferramenta para desenho de autômatos e grafos <br><br>
 * @version 2.0
 *
 * @author Kleber Kruger
 * @author José Ademar Peixoto de Souza
 */
public class Transition extends vEdge implements Serializable {

    private String name; // Nome da transição
    private transient State sourceState; // Estado de origem da transição
    private transient State targetState; // Estado de destino da transição
    private transient TransitionType type; // Tipo da transição
    private boolean dimmed; // Verdadeiro quando a transição for do tipo esmaecida

    /**
     * Cria uma transição vazia
     */
    public Transition() {

        super();

        name = null;
        sourceState = null;
        targetState = null;
        type = null;
    }

    /**
     * Cria uma transição
     * 
     * @param name
     * Nome da transição
     * 
     * @param sourceState
     * Estado inicial da transição
     * 
     * @param targetState
     * Estado destino da transição
     * 
     * @param type
     * Tipo da transição
     */
    public Transition(String name, State sourceState, State targetState,
            TransitionType type) {

        super(name);

        this.name = name;
        this.sourceState = sourceState;
        this.targetState = targetState;
        this.type = type;

        configure();
    }

    /**
     * Cria uma cópia da transição
     * @param transition
     */
    public Transition(Transition transition) {

        super(transition.getName());

        name = transition.getName();
        sourceState = transition.getSourceState();
        targetState = transition.getTargetState();
        type = transition.getType();

        configure();
    }

    private void configure() {

        // Configurações padrão da célula
        GraphConstants.setDisconnectable(attributes, false);
        GraphConstants.setEndFill(attributes, true);
        GraphConstants.setLabelAlongEdge(attributes, true);
        GraphConstants.setLineWidth(attributes, new Float(1.3));

        // Configurações específicas da célula
        if (type == TransitionType.EDGE_UNDIRECTED) {
            // Aresta sem seta - Grafo não-dirigido
            GraphConstants.setLineEnd(attributes, GraphConstants.ARROW_NONE);

        } else {
            // Aresta com seta - Autômato/Grafo dirigido
            GraphConstants.setLineEnd(attributes, GraphConstants.ARROW_TECHNICAL);
        }

        if (type == TransitionType.ARC_TRANSITION) {
            // Transição em arco
            GraphConstants.setRouting(attributes, getRoute());
        }
    }

    private Routing getRoute() {
        return new Routing() {

            public List route(GraphLayoutCache cache, EdgeView edge) {
                return getPoints();
            }

            public int getPreferredLineStyle(EdgeView edge) {
                return GraphConstants.STYLE_BEZIER;
            }
        };
    }

    private List getPoints() {
        List points = new ArrayList<Point>();

        Point2D sourcePoint = new Point2D.Double(StateUtils.getCenterX(
                sourceState), StateUtils.getCenterY(sourceState));

        Point2D targetPoint = new Point2D.Double(StateUtils.getCenterX(
                targetState), StateUtils.getCenterY(targetState));

        points.add(sourcePoint);
        points.add(MathPoints.getBezierMiddlePoint(sourcePoint, targetPoint));
        points.add(targetPoint);

        return points;
    }

    @Override
    public void setUserObject(Object userObject) {
        String newName = userObject.toString();
        if (type == TransitionType.EDGE_DIRECTED
                || type == TransitionType.EDGE_UNDIRECTED) {

            if (VariableType.isDigits(newName)) {
                name = newName;

                super.setUserObject(name);
                nameChanged = true;
            }

        } else {
            name = newName;
            super.setUserObject(name);
            nameChanged = true;
        }

    }

    /**
     * @return o nome da transição
     */
    public String getName() {
        return name;
    }

    /**
     * @param
     * define o nome da transição
     */
    public void setName(String name) {
        this.name = name;
        setUserObject(name);
    }

    /**
     * @return o estado inicial (SourceState) da transição
     */
    public State getSourceState() {
        return sourceState;
    }

    /**
     * @param sourceState
     * define o estado inicial (SourceState)
     */
    public void setSourceState(State sourceState) {
        this.sourceState = sourceState;
    }

    /**
     * @return o estado destino da transição
     */
    public State getTargetState() {
        return targetState;
    }

    /**
     * @param targetState
     * define o estado destino (targetState)
     */
    public void setTargetState(State targetState) {
        this.targetState = targetState;
    }

    /**
     * @return o tipo da transição
     */
    public TransitionType getType() {
        return type;
    }

    /**
     * @param type
     * define o tipo da transição
     */
    public void setType(TransitionType type) {
        this.type = type;
    }

    /**
     * @return um booleano que indica verdadeiro quando a transição for do tipo
     * esmaecida
     */
    public boolean isDimmed() {
        return dimmed;
    }

    /**
     * @param dimmed
     * um booleano que indica se a transição é esmaecida
     */
    public void setDimmed(boolean dimmed) {
        this.dimmed = dimmed;
    }
}
