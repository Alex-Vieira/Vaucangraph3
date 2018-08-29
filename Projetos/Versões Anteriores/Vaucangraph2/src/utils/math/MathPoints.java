package utils.math;

import java.awt.geom.Point2D;

/**
 * Classe que possui métodos utilitários para se calcular algumas coordenadas
 * em arestas/transições
 *
 * @author Gedson Faria
 * @author Kleber Kruger
 * @author José Ademar Peixoto de Souza
 */
public class MathPoints {

    /**
     * Obtém a posição do label em arestas bezier
     *
     * @param p1
     * ponto de origem
     *
     * @param p2
     * ponto de destino
     *
     * @return
     * o ponto médio acima da seta
     */
    public static Point2D getLabelPosition(Point2D p1, Point2D p2) {

        double dx, dy, hip, AlphaAngle, px, py, newx, newy;

        dx = p2.getX() - p1.getX();
        dy = p2.getY() - p1.getY();

        hip = (Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2))) / 2;
        AlphaAngle = Math.atan2(dy, dx);

        px = hip;
        py = -50.0;

        newx = (px * Math.cos(AlphaAngle)) - (py * Math.sin(AlphaAngle));
        newy = (px * Math.sin(AlphaAngle)) + (py * Math.cos(AlphaAngle));

        return new Point2D.Double(newx, newy);
    }

    /**
     * @param p1
     * ponto de origem
     * 
     * @param p2
     * ponto de destino
     * 
     * @return
     * o ponto médio da aresta bezier
     */
    public static Point2D getBezierMiddlePoint(Point2D p1, Point2D p2) {

        double dx, dy, hip, AlphaAngle, px, py, newx, newy, xfinal, yfinal;

        dx = p2.getX() - p1.getX();
        dy = p2.getY() - p1.getY();

        hip = (Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2))) / 2;
        AlphaAngle = Math.atan2(dy, dx);

        px = hip;
        py = -50.0;

        newx = (px * Math.cos(AlphaAngle)) - (py * Math.sin(AlphaAngle));
        newy = (px * Math.sin(AlphaAngle)) + (py * Math.cos(AlphaAngle));

        xfinal = (p1.getX() + newx);
        yfinal = (p1.getY() + newy);

        return new Point2D.Double(xfinal, yfinal);
    }
}
