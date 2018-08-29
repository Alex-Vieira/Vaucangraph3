package utils.math;

/**
 * Classe utilitária para conversão de medidas
 * @author José Ademar Peixoto de Souza
 */
public class MetricConversor {

    // one pixel has 0.035277778cm
    private static final double onePixel = 0.035277778;
    //one centimeter has 28.346456693px
    private static final double oneCentimeter = 28.346456693;

    /**
     * Método utilitário que converte o valor de centímetros em pixels
     * @param centimeters
     * @return o valor em centímetros
     */
    public static int toPixels(double centimeters) {
        return (int) (centimeters * oneCentimeter);
    }

    /**
     * Método utilitário que converte o valor de pixels para centímetros
     * @param pixels
     * @return o valor em pixels
     */
    public static double toCentimeters(double pixels) {
        return (pixels * onePixel);
    }
}
