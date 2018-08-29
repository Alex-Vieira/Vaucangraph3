package utils.state;

import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import jgraph.model.vGraphCell;
import jgraph.view.Canvas;
import org.jgraph.graph.CellView;
import utils.system.VariableType;
import vaucangraph.model.enumeration.ProjectType;
import vaucansong.bean.State;
import vaucansong.bean.enumeration.StateSize;

/**
 *
 * @author Kleber Kruger
 */
public class StateUtils {

    /**
     * Método utilitário que calcula a dimensão total da célula
     * (tamanho do estado + tamanho da flecha) de acordo com o estado recebido
     * por parâmetro
     *
     * @param state o estado
     * @return a dimensão total da célula (flecha + estado)
     */
    public static Dimension2D getStateSize(boolean initial, StateSize size) {

        Dimension2D dimension = getSize(size);
        int widthArrow = getWidthArrow(initial, size);

        int width = (int) (widthArrow + dimension.getWidth());
        int height = (int) dimension.getHeight();

        dimension.setSize(width, height);
        return dimension;
    }

    /**
     * Método utilitário que calcula a dimensão total da célula
     * (tamanho do estado + tamanho da flecha) de acordo com o estado recebido
     * por parâmetro
     *
     * @param state o estado
     * @return a dimensão total da célula (flecha + estado)
     */
    public static Dimension2D getStateSize(State state) {

        Dimension2D dimension = getSize(state.getSize());
        int widthArrow = getWidthArrow(state);

        int width = (int) (widthArrow + dimension.getWidth());
        int height = (int) dimension.getHeight();

        dimension.setSize(width, height);
        return dimension;
    }

    /**
     * Método utilitário que calcula o tamanho da célula de acordo com o estado
     * recebido por parâmetro
     *
     * @param size o tamanho do estado
     * @return a dimensão da célula conforme o estado
     */
    private static Dimension2D getSize(StateSize size) {
        Dimension2D dimension = null;

        switch (size) {

            case VERY_SMALL:
                dimension = new Dimension(11, 11);
                break;

            case SMALL:
                dimension = new Dimension(22, 22);
                break;

            case MEDIUM:
                dimension = new Dimension(34, 34);
                break;

            case LARGE:
                dimension = new Dimension(45, 45);
                break;
        }
        return dimension;
    }

    /**
     * Método utilitário que calcula o tamanho da flecha do estado inicial
     * @param state o estado inicial
     * @return o tamanho da flecha
     */
    public static int getWidthArrow(State state) {
        int widthArrow = 0;
        if (state.isInitial()) {
            widthArrow = (int) (getSize(state.getSize()).getWidth() * 0.4);
        }
        return widthArrow;
    }

    /**
     * Método utilitário que calcula o tamanho da flecha do estado inicial
     * @param state o estado inicial
     * @return o tamanho da flecha
     */
    public static int getWidthArrow(boolean initial, StateSize size) {
        int widthArrow = 0;
        if (initial) {
            widthArrow = (int) (getSize(size).getWidth() * 0.3);
        }
        return widthArrow;
    }

    public static double getXToCentralize(State state, double x) {
        // Obtém a largura do estado
        int width = (int) getStateSize(false, state.getSize()).getWidth();
        int widhtArrow = getWidthArrow(state);

        // Centraliza o estado para o meio da setinha do mouse.
        return x - (width / 2) - widhtArrow;
    }

    public static double getYToCentralize(State state, double y) {
        // Obtém a altura do estado
        double height = getStateSize(state).getHeight();

        // Centraliza o estado para o meio da setinha do mouse.
        return y - (height / 2);
    }

    /**
     * Método utilitário que obtém a CellView da célula
     * @param cell
     * @param canvas 
     * @return a CellView da célula
     */
    public static CellView getCellView(vGraphCell cell, Canvas canvas) {
        return canvas.getGraphLayoutCache().getMapping(cell, true);
    }

    /**
     * Método utilitário que obtém o ponto x do estado
     * 
     * @param state 
     * o estado
     * 
     * @param canvas 
     * o canvas
     * 
     * @return o ponto x do estado
     */
    public static double getX(State state, Canvas canvas) {
        return getCellView(state, canvas).getBounds().getX();
    }

    /**
     * Método utilitário que obtém o ponto y do estado
     *
     * @param state 
     * o estado
     * 
     * @param canvas 
     * o canvas
     * 
     * @return o ponto y do estado
     */
    public static double getY(State state, Canvas canvas) {
        return getCellView(state, canvas).getBounds().getY();
    }

    /**
     * Método utilitário que calcula o ponto x central do estado
     * @param state o estado
     * @return o ponto x central do estado
     */
    public static double getCenterX(State state) {
        int width = (int) getStateSize(false, state.getSize()).getWidth();
        int widhtArrow = getWidthArrow(state);

        return state.getX() + widhtArrow + width / 2;
    }

    /**
     * Método utilitário que calcula o ponto y central do estado
     * @param state o estado
     * @return o ponto y central do estado
     */
    public static double getCenterY(State state) {
        int width = (int) getStateSize(false, state.getSize()).getHeight();
        return state.getY() + width / 2;
    }

    /**
     * Método utilitário que calcula o ponto x central da célula
     * @param state o estado
     * @return o ponto x central da célula
     */
    public static double getCellCenterX(State state) {
        int width = (int) getStateSize(false, state.getSize()).getWidth();
        return state.getX() + width / 2;
    }

    /**
     * Método utilitário que calcula o ponto y central da célula
     * @param state o estado
     * @return o ponto y central da célula
     */
    public static double getCellCenterY(State state) {
        return getCenterY(state);
    }

    /**
     * Verifica se o nome do user object é valido
     * @param name
     * @param projectType
     * @return
     */
    public static boolean isUserObjectValid(String name, ProjectType projectType) {
        if (projectType == ProjectType.GRAPH_DIRECTED
                || projectType == ProjectType.GRAPH_UNDIRECTED) {

            if (!VariableType.isDigits(name)) {
                return false;
            }
        }
        return true;
    }
}
