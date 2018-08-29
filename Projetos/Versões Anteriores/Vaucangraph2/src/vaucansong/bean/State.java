package vaucansong.bean;

import java.awt.Color;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.border.LineBorder;
import jgraph.model.vGraphCell;
import org.jgraph.graph.GraphConstants;
import utils.state.StateUtils;
import utils.system.VariableType;
import vaucangraph.model.enumeration.ProjectType;
import vaucansong.bean.enumeration.StateSize;

/**
 * Implementação de um estado em um grafo ou autômato.
 *
 * <br><br><br>
 * Vaucangraph 2 - Ferramenta para desenho de autômatos e grafos <br><br>
 * @version 2.0
 *
 * @author Kleber Kruger
 * @author José Ademar Peixoto de Souza
 */
public class State extends vGraphCell implements Serializable {

    /**
     * Atributos que definem as propriedades de um estado
     */
    private int id; // Id (código) do estado
    private String name; // Nome do estado
    private boolean initial; // Verdadeiro quando o estado for inicial
    private boolean end; // Verdadeiro quando o estado for final
    private boolean dimmed; // Verdadeiro quando o estado for do tipo esmaecido
    private boolean hidden; // Verdadeiro quando o estado for oculto
    private boolean variableSize; // Verdadeiro se o tamanho do estado variar
    private int x, y; // Determina os pontos X,Y do estado
    private StateSize size; // Determina o tamanho do estado (Very Small, Small, Medium, Large)
    private ProjectType type; // Determina o tipo de célula (estado - autômato, vértice - grafo)
    private ArrayList<Transition> transitions; // ArrayList que guarda todas as transições
    /**
     * Atributos que definem as propriedades de uma célula
     */
    private transient boolean selected;
    private transient boolean focused;
    private transient boolean preview;

    /**
     * Cria um estado vazio
     */
    public State() {
        super();

        // Inicializa os atributos como null
        name = null;
        size = null;
        transitions = new ArrayList<Transition>();
    }

    /**
     * Cria uma célula que representa um vértice de um grafo
     *
     * @param id
     * Id (código) do estado
     *
     * @param name
     * Nome do estado
     *
     * @param x
     * Posição X do estado no canvas
     *
     * @param y
     * Posição Y do estado no canvas
     */
    public State(int id, String name, int x, int y, StateSize size,
            ProjectType type) {

        super(name);

        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.size = size;
        this.type = type;

        // Define valores padrão para alguns atributos
        initial = false;
        end = false;

        // Propriedades do estado:
        dimmed = false;
        hidden = false;
        variableSize = false;

        // Propriedades da célula:
        selected = false;
        focused = false;
        preview = false;

        transitions = new ArrayList<Transition>();

        configure();
    }

    /**
     * Cria uma célula que representa um estado em um autômato
     *
     * @param id
     * Id (código) do estado
     *
     * @param name
     * Nome do estado
     *
     * @param initial
     * Verdadeiro quando o estado for inicial
     *
     * @param end
     * Verdadeiro quando o estado for final
     *
     * @param x
     * Posição X do estado no canvas
     *
     * @param y
     * Posição Y do estado no canvas
     *
     * @param size
     * Tamanho do estado - <ENUMERATION> StateSize <br><br>
     * Exemplos: <br><br>
     * StateSize.VERY_SMALL
     * StateSize.SMALL
     * StateSize.MEDIUM
     * StateSize.LARGE
     */
    public State(int id, String name, boolean initial, boolean end,
            int x, int y, StateSize size) {

        super(name);

        this.id = id;
        this.name = name;
        this.initial = initial;
        this.end = end;
        this.x = x;
        this.y = y;
        this.size = size;
        this.type = ProjectType.AUTOMATON;

        // Define valores padrão para alguns atributos

        // Propriedades do estado:
        dimmed = false;
        hidden = false;
        variableSize = false;

        // Propriedades da célula:
        selected = false;
        focused = false;
        preview = false;

        transitions = new ArrayList<Transition>();

        configure();
    }

    /**
     * Cria a cópia de uma célula
     * @param state
     */
    public State(State state) {

        super(state.getName());

        // Copia os atributos da célula
        id = state.getId();
        name = state.getName();

        initial = state.isInitial();
        end = state.isFinal();

        dimmed = state.isDimmed();
        hidden = state.isHidden();
        variableSize = state.isVariableSize();

        x = state.getX();
        y = state.getY();

        size = state.getSize();
        type = state.getType();

        // Define valores padrão para alguns atributos

        // Propriedades do estado:
        dimmed = false;
        hidden = false;
        variableSize = false;

        // Propriedades da célula:
        selected = false;
        focused = false;
        preview = false;

        transitions = new ArrayList<Transition>();

        configure();
    }

    /**
     * Método que configura os atributos da célula
     * @param projectType tipo do projeto
     */
    private void configure() {

        if (type == ProjectType.GRAPH_DIRECTED
                || type == ProjectType.GRAPH_UNDIRECTED) {

            initial = false;
            end = false;
        }

        GraphConstants.setAutoSize(attributes, variableSize);

        // Define o modelo da borda da célula
        GraphConstants.setBorder(attributes,
                new LineBorder(new Color(51, 153, 255), 3, true));
        // Define a cor da borda
        GraphConstants.setBorderColor(attributes, new Color(51, 153, 255));

        // Define as bordas da célula
        Dimension2D d = StateUtils.getStateSize(this);
        GraphConstants.setBounds(attributes,
                new Rectangle2D.Double(x, y, d.getWidth(), d.getHeight()));

        // Define o valor da célula para editável
        GraphConstants.setEditable(attributes, true);

        // Define a propriedade que permite a célula alterar de tamanho) de acordo com o atributo variableSize
        GraphConstants.setSizeable(attributes, variableSize);

        GraphConstants.setSizeableAxis(attributes, 3);
    }

    @Override
    public void setUserObject(Object userObject) {
        String newName = userObject.toString();
        if (type == ProjectType.GRAPH_DIRECTED
                || type == ProjectType.GRAPH_UNDIRECTED) {

            if (VariableType.isDigits(newName)) {
                name = newName;
                String label = name;
                if (size == StateSize.VERY_SMALL) {
                    label = "";
                }
                super.setUserObject(label);
                nameChanged = true;
            }
        } else {
            name = newName;
            String label = name;
            if (size == StateSize.VERY_SMALL) {
                label = "";
            }
            super.setUserObject(label);
            nameChanged = true;
        }
    }

    /**
     * Atualiza e reconfigura a célula
     */
    public void refresh() {
        configure();
    }

    /**
     * @return o id (código) do estado
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     * define o id (código) do estado
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return o nome do estado
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     * define o nome do estado
     */
    public void setName(String name) {
        this.name = name;
        setUserObject(name);
    }

    /**
     * @return um booleano que indica verdadeiro caso o estado seja inicial
     */
    public boolean isInitial() {
        return initial;
    }

    /**
     * @param initial
     * um booleano que indica se o estado é inicial
     */
    public void setInitial(boolean initial) {
        this.initial = initial;
    }

    /**
     * @return um booleano que indica verdadeiro caso o estado seja final.
     */
    public boolean isFinal() {
        return end;
    }

    /**
     * @param end
     * um booleano que indica se o estado é final
     */
    public void setFinal(boolean end) {
        this.end = end;
    }

    /**
     * @return um booleano que indica verdadeiro quando o estado for do tipo
     * esmaecido
     */
    public boolean isDimmed() {
        return dimmed;
    }

    /**
     * @param dimmed
     * um booleano que indica se o estado é esmaecido
     */
    public void setDimmed(boolean dimmed) {
        this.dimmed = dimmed;
    }

    /**
     * @return um booleano que indica verdadeiro quando o estado é oculto
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * @param hidden
     * um booleano que indica se o estado é oculto
     */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    /**
     * @return um booleano que indica verdadeiro quando o estado tem tamanho
     * variável
     */
    public boolean isVariableSize() {
        return variableSize;
    }

    /**
     * @param variableSize
     * um booleano que indica se o estado tem tamanho variável
     */
    public void setVariableSize(boolean variableSize) {
        this.variableSize = variableSize;
    }

    /**
     * @return a posição X do estado
     */
    public int getX() {
        return x;
    }

    /**
     * @param x
     * altera a posição X do estado
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return a posição Y do estado
     */
    public int getY() {
        return y;
    }

    /**
     * @param y
     * altera a posição Y do estado
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return o tamanho do estado <br>
     * 
     * Very Small - Muito pequeno
     * Small - Pequeno
     * Medium - Médio
     * Large - Grande
     */
    public StateSize getSize() {
        return size;
    }

    /**
     * @param size
     * altera o tamanho do estado
     */
    public void setSize(StateSize size) {
        this.size = size;
    }

    /**
     * @return o tipo da célula <br>
     * AUTOMATON - Estado de um autômato
     * GRAPH_DIRECTED - Vértice de um grafo dirigido
     * GRAPH_DIRECTED - Vértice de um grafo não-dirigido
     */
    public ProjectType getType() {
        return type;
    }

    /**
     * @param type
     * altera o tamanho do estado
     */
    public void setType(ProjectType type) {
        this.type = type;
    }

    /**
     * @return um ArrayList de transições
     */
    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

    /**
     * @param transitions
     * altera o ArrayList de transições do estado
     */
    public void setTransitions(ArrayList<Transition> transitions) {
        this.transitions = transitions;
    }

    /**
     * @return um booleano que indica se a célula está em focus
     */
    public boolean isFocused() {
        return focused;
    }

    /**
     * @param focused
     * um booleano que indica se a célula está em focus
     */
    public void setFocused(boolean focused) {
        this.focused = focused;
    }

    /**
     * @return um booleano que indica se a célula é pré-visualização
     */
    public boolean isPreview() {
        return preview;
    }

    /**
     * @param focused
     * um booleano que indica se a célula é pré-visulização
     */
    public void setPreview(boolean preview) {
        this.preview = preview;
    }

    /**
     * @return um booleano que indica se a célula está selecionada
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * @param focused
     * um booleano que indica se a célula está selecionada
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
