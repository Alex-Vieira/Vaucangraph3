package vaucangraph.model;

import java.awt.Dimension;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import vaucangraph.model.enumeration.ProjectType;
import vaucansong.bean.State;
import vaucansong.bean.Transition;

/**
 *
 * @author Kleber Kruger
 */
public class Project implements Serializable {

    /**
     * Atributos que definem as informações do arquivo do projeto
     */
    private String local;
    private boolean saved;
    /**
     * Atributos que contém informações do projeto
     */
    private String name;
    private ProjectType type;
    private Dimension size;
    private ArrayList<State> states;
    private ArrayList<Transition> transitions;
    /**
     * Atributo que contém o código vaucanson
     */
    private String vaucanson;

    /**
     * Cria um projeto vazio
     */
    public Project() {
        size = null;
        states = null;
        transitions = null;
    }

    /**
     * Cria um novo projeto
     * @param name
     * o nome do projeto
     *
     * @param dimension
     * a dimensão do projeto
     *
     * @param local
     * o local do arquivo que contém as informações do projeto
     */
    public Project(String name, ProjectType type, Dimension dimension) {
        // Define os atributos
        this.name = name;
        this.type = type;
        this.size = dimension;

        // Inicializa os ArrayList de estados e transições
        states = new ArrayList<State>();
        transitions = new ArrayList<Transition>();
        saved = true;
    }

    /**
     * @return the local
     */
    public String getLocal() {
        return local;
    }

    /**
     * @param local the local to set
     */
    public void setLocal(String local) {
        this.local = local;
    }

    /**
     * @return the saved
     */
    public boolean isSaved() {
        return saved;
    }

    /**
     * @param saved the saved to set
     */
    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public ProjectType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(ProjectType type) {
        this.type = type;
    }

    /**
     * @return the size
     */
    public Dimension getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Dimension size) {
        this.size = size;
    }

    /**
     * @return the states
     */
    public ArrayList<State> getStates() {
        return states;
    }

    /**
     * @param states the states to set
     */
    public void setStates(ArrayList<State> states) {
        this.states = states;
    }

    /**
     * @return the transitions
     */
    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

    /**
     * @param transitions the transitions to set
     */
    public void setTransitions(ArrayList<Transition> transitions) {
        this.transitions = transitions;
    }

    /**
     * @return the vaucanson code
     */
    public String getVaucanson() {
        return vaucanson;
    }

    /**
     * @param vaucanson the vaucanson code to set
     */
    public void setVaucanson(String vaucanson) {
        this.vaucanson = vaucanson;
    }

    /**
     * @return the project file
     */
    public File getFile() {
        return new File(local);
    }
}
