package vaucangraph.model.enumeration;

/**
 * Enum que define o tipo do projeto
 * @author Kleber Kruger
 */
public enum ProjectType {

    /**
     * @return o tipo do projeto
     */
    AUTOMATON, GRAPH_DIRECTED, GRAPH_UNDIRECTED;

    @Override
    public String toString() {
        if (super.toString().equals("AUTOMATON")) {
            return "Autômato";
        }
        if (super.toString().equals("GRAPH_DIRECTED")) {
            return "Grafo dirigido";
        }
        if (super.toString().equals("GRAPH_UNDIRECTED")) {
            return "Grafo não-dirigido";
        }
        return super.toString();
    }
}
