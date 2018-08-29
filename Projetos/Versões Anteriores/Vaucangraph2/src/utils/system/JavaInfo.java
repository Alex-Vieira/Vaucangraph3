/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.system;

import utils.formatters.Formatter;

/**
 * Classe que contém métodos utilitários para obter informações do compilador Java
 *
 * @author Kleber Kruger
 * @author JDownloader
 */
public class JavaInfo {

    public static Double getJavaVersion() {
        String version = System.getProperty("java.version");
        int majorVersion = Formatter.filterInt(version.substring(0, version.indexOf(".")));
        int subversion = Formatter.filterInt(version.substring(version.indexOf(".") + 1));
        return Double.parseDouble(majorVersion + "." + subversion);
    }
}
