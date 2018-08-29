package vaucangraph.enumeration;

public enum TipoDeProjeto {

    /**
     * @return o tipo do projeto
     */
    AUTOMATO, GRAFO_DIRECIONADO, GRAFO_NAO_DIRECIONADO;

    @Override
    public String toString() {
        if (super.toString().equals("AUTOMATO")) {
            return "Autômato";
        }
        if (super.toString().equals("GRAFO_DIRECIONADO")) {
            return "Grafo dirigido";
        }
        if (super.toString().equals("GRAFO_NAO_DIRECIONADO")) {
            return "Grafo não-dirigido";
        }
        return super.toString();
    }
}
