/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.system;

/**
 *
 * @author Kleber Kruger
 */
public class VariableType {

    /**
     * Verifica se a string é composta apenas de letras
     * @param string
     * @return verdadeiro caso a string seja composta apenas de letras
     */
    public static boolean isLetters(String string) {
        char[] charArray = string.toCharArray();
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isLetter(charArray[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica se a string é composta apenas de dígitos
     * @param string
     * @return verdadeiro caso a string seja composta apenas de letras
     */
    public static boolean isDigits(String string) {
        char[] charArray = string.toCharArray();
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isDigit(charArray[i])) {
                return false;
            }
        }
        return true;
    }
}
