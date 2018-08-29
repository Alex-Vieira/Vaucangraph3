package vaucangraph.components.propertytable.model;

import vaucangraph.model.enumeration.ProjectType;

/**
 * Campos da tabela de propriedades
 * @author Kleber Kruger
 */
public class TableFields extends TableField {

    /**
     * Índice das colunas da tabela de propriedades
     */
    public static final int COLUMN_PROPERTY = 0;
    public static final int COLUMN_VALUE = 1;
    /**
     * Índice das linhas da tabela de propriedades
     */
    public static final int ROW_NAME = 0;
    public static final int ROW_SIZE = 1;
    /**
     * Índice das linhas da tabela de autômatos
     */
    public static final int ROW_INITIAL = 2;
    public static final int ROW_FINAL = 3;
    public static final int ROW_DIMMED_AUTOMATON = 4;
    public static final int ROW_HIDDEN_AUTOMATON = 5;
    public static final int ROW_VARIABLESIZE_AUTOMATON = 6;
    public static final int ROW_POSX_AUTOMATON = 7;
    public static final int ROW_POSY_AUTOMATON = 8;
    /**
     * Índice das linhas da tabela de grafos
     */
    public static final int ROW_DIMMED_GRAPH = 2;
    public static final int ROW_HIDDEN_GRAPH = 3;
    public static final int ROW_VARIABLESIZE_GRAPH = 4;
    public static final int ROW_POSX_GRAPH = 5;
    public static final int ROW_POSY_GRAPH = 6;
    
    public static final String COLUMNNAME_PROPERTY = "Propriedade";
    public static final String COLUMNNAME_VALOR = "Valor";

    /**
     * Nome dos campos da coluna "propriedade"
     */
    public static final String PROPERTYNAME_NAME = " Nome ";
    public static final String PROPERTYNAME_SIZE = " Tamanho ";
    public static final String PROPERTYNAME_INITIAL = " Inicial ";
    public static final String PROPERTYNAME_FINAL = " Final ";
    public static final String PROPERTYNAME_DIMMED = " Esmaecido ";
    public static final String PROPERTYNAME_HIDDEN = " Oculto ";
    public static final String PROPERTYNAME_VARIABLESIZE = " Tamanho variável ";
    public static final String PROPERTYNAME_POSX = " Posição X ";
    public static final String PROPERTYNAME_POSY = " Posição Y ";

    /**
     * 
     * @param type
     * @return
     */
    public static TableField get_NAME_PROPERTY(ProjectType type) {
        return new TableField(ROW_NAME, COLUMN_PROPERTY, PROPERTYNAME_NAME);
    }

    /**
     * 
     * @param type
     * @return
     */
    public static TableField get_NAME_VALUE(ProjectType type) {
        return new TableField(ROW_NAME, COLUMN_VALUE, "");
    }

    /**
     * 
     * @param type
     * @return
     */
    public static TableField get_SIZE_PROPERTY(ProjectType type) {
        return new TableField(ROW_SIZE, COLUMN_PROPERTY, PROPERTYNAME_SIZE);
    }

    /**
     * 
     * @param type
     * @return
     */
    public static TableField get_SIZE_VALUE(ProjectType type) {
        return new TableField(ROW_SIZE, COLUMN_VALUE, "");
    }

    /**
     * 
     * @param type
     * @return
     */
    public static TableField get_INITIAL_PROPERTY(ProjectType type) {
        if (type == ProjectType.AUTOMATON) {
            return new TableField(ROW_INITIAL, COLUMN_PROPERTY, PROPERTYNAME_INITIAL);
        }
        return null;
    }

    /**
     * 
     * @param type
     * @return
     */
    public static TableField get_INITIAL_VALUE(ProjectType type) {
        if (type == ProjectType.AUTOMATON) {
            return new TableField(ROW_INITIAL, COLUMN_VALUE, false);
        }
        return null;
    }

    /**
     * 
     * @param type
     * @return
     */
    public static TableField get_FINAL_PROPERTY(ProjectType type) {
        if (type == ProjectType.AUTOMATON) {
            return new TableField(ROW_FINAL, COLUMN_PROPERTY, PROPERTYNAME_FINAL);
        }
        return null;
    }

    /**
     * 
     * @param type
     * @return
     */
    public static TableField get_FINAL_VALUE(ProjectType type) {
        if (type == ProjectType.AUTOMATON) {
            return new TableField(ROW_FINAL, COLUMN_VALUE, false);
        }
        return null;
    }

    /**
     * 
     * @param type
     * @return
     */
    public static TableField get_DIMMED_PROPERTY(ProjectType type) {
        if (type == ProjectType.AUTOMATON) {
            return new TableField(ROW_DIMMED_AUTOMATON, COLUMN_PROPERTY, PROPERTYNAME_DIMMED);
        } else {
            return new TableField(ROW_DIMMED_GRAPH, COLUMN_PROPERTY, PROPERTYNAME_DIMMED);
        }
    }

    /**
     * 
     * @param type
     * @return
     */
    public static TableField get_DIMMED_VALUE(ProjectType type) {
        if (type == ProjectType.AUTOMATON) {
            return new TableField(ROW_DIMMED_AUTOMATON, COLUMN_VALUE, false);
        } else {
            return new TableField(ROW_DIMMED_GRAPH, COLUMN_VALUE, false);
        }
    }

    /**
     * 
     * @param type
     * @return
     */
    public static TableField get_HIDDEN_PROPERTY(ProjectType type) {
        if (type == ProjectType.AUTOMATON) {
            return new TableField(ROW_HIDDEN_AUTOMATON, COLUMN_PROPERTY, PROPERTYNAME_HIDDEN);
        } else {
            return new TableField(ROW_HIDDEN_GRAPH, COLUMN_PROPERTY, PROPERTYNAME_HIDDEN);
        }
    }

    /**
     * 
     * @param type
     * @return
     */
    public static TableField get_HIDDEN_VALUE(ProjectType type) {
        if (type == ProjectType.AUTOMATON) {
            return new TableField(ROW_HIDDEN_AUTOMATON, COLUMN_VALUE, false);
        } else {
            return new TableField(ROW_HIDDEN_GRAPH, COLUMN_VALUE, false);
        }
    }

    /**
     * 
     * @param type
     * @return
     */
    public static TableField get_VARIABLESIZE_PROPERTY(ProjectType type) {
        if (type == ProjectType.AUTOMATON) {
            return new TableField(ROW_VARIABLESIZE_AUTOMATON, COLUMN_PROPERTY, PROPERTYNAME_VARIABLESIZE);
        } else {
            return new TableField(ROW_VARIABLESIZE_GRAPH, COLUMN_PROPERTY, PROPERTYNAME_VARIABLESIZE);
        }
    }

    /**
     * 
     * @param type
     * @return
     */
    public static TableField get_VARIABLESIZE_VALUE(ProjectType type) {
        if (type == ProjectType.AUTOMATON) {
            return new TableField(ROW_VARIABLESIZE_AUTOMATON, COLUMN_VALUE, false);
        } else {
            return new TableField(ROW_VARIABLESIZE_GRAPH, COLUMN_VALUE, false);
        }
    }

    /**
     * 
     * @param type
     * @return
     */
    public static TableField get_POSX_PROPERTY(ProjectType type) {
        if (type == ProjectType.AUTOMATON) {
            return new TableField(ROW_POSX_AUTOMATON, COLUMN_PROPERTY, PROPERTYNAME_POSX);
        } else {
            return new TableField(ROW_POSX_GRAPH, COLUMN_PROPERTY, PROPERTYNAME_POSX);
        }
    }

    /**
     * 
     * @param type
     * @return
     */
    public static TableField get_POSX_VALUE(ProjectType type) {
        if (type == ProjectType.AUTOMATON) {
            return new TableField(ROW_POSY_AUTOMATON, COLUMN_VALUE, "");
        } else {
            return new TableField(ROW_POSY_GRAPH, COLUMN_VALUE, "");
        }
    }

    /**
     * 
     * @param type
     * @return
     */
    public static TableField get_POSY_PROPERTY(ProjectType type) {
        if (type == ProjectType.AUTOMATON) {
            return new TableField(ROW_POSY_AUTOMATON, COLUMN_PROPERTY, PROPERTYNAME_POSY);
        } else {
            return new TableField(ROW_POSY_GRAPH, COLUMN_PROPERTY, PROPERTYNAME_POSY);
        }
    }

    /**
     * 
     * @param type
     * @return
     */
    public static TableField get_POSY_VALUE(ProjectType type) {
        if (type == ProjectType.AUTOMATON) {
            return new TableField(ROW_POSY_AUTOMATON, COLUMN_VALUE, "");
        } else {
            return new TableField(ROW_POSY_GRAPH, COLUMN_VALUE, "");
        }
    }
}
