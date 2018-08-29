package vaucansong.bean.enumeration;

/**
 * Enum que define o tamanho do estado.
 * @author Kleber Kruger
 */
public enum StateSize {

    /**
     * @return o tamanho do estado <br><br>
     *
     * VERY_SMALL - 11 pixels
     * SMALL - 22 pixels
     * MEDIUM - 34 pixels
     * LARGE - 44 pixels
     */
    VERY_SMALL, SMALL, MEDIUM, LARGE;

    @Override
    public String toString() {
        if (super.toString().equals("VERY_SMALL")) {
            return "Muito pequeno";
        }
        if (super.toString().equals("SMALL")) {
            return "Pequeno";
        }
        if (super.toString().equals("MEDIUM")) {
            return "Médio";
        }
        if (super.toString().equals("LARGE")) {
            return "Grande";
        }
        return super.toString();
    }
}
