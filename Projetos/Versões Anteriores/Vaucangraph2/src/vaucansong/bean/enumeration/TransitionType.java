package vaucansong.bean.enumeration;

/**
 * Enum que define o tipo da transição.
 * @author Kleber Kruger
 */
public enum TransitionType {

    /**
     * @return o tipo da aresta
     */
    EDGE_DIRECTED, EDGE_UNDIRECTED,
    /**
     * @return o tipo da transição
     */
    SIMPLE_TRANSITION, SELF_TRANSITION, ARC_TRANSITION;

    @Override
    public String toString() {
        if (super.toString().equals("EDGE_DIRECTED")) {
            return super.toString();
        }
        if (super.toString().equals("EDGE_UNDIRECTED")) {
            return super.toString();
        }
        if (super.toString().equals("SIMPLE_TRANSITION")) {
            return super.toString();
        }
        if (super.toString().equals("SELF_TRANSITION")) {
            return super.toString();
        }
        if (super.toString().equals("ARC_TRANSITION")) {
            return super.toString();
        }
        return super.toString();
    }
}
