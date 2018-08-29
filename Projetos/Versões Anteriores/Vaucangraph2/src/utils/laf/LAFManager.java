/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.laf;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import utils.system.JavaInfo;
import utils.system.OSDetector;

/**
 * Classe que possui métodos utilitários para Look And Feel
 * @author Kleber Kruger
 */
public class LAFManager {

    /**
     * Define a decoração da barra de título
     * @param decorated
     */
    public static void setDefaultLAFDecorated(boolean decorated) {
        JFrame.setDefaultLookAndFeelDecorated(decorated);
        JDialog.setDefaultLookAndFeelDecorated(decorated);
    }

    /**
     * Define o atual look and feel (tema) da aplicação de acordo com o tema 
     * recebido por parâmetro
     * 
     * @param lookAndFeel novo look and feel
     */
    public static void setLookAndFeel(LookAndFeel lookAndFeel) {
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LAFManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static LookAndFeel getDefaultLookAndFeel() {
        // Windows
        if (OSDetector.isWindows()) {
            return new WindowsLookAndFeel();
        } // Linux
        else if (OSDetector.isLinux()) {
            // Gnome
            if (OSDetector.isGnome()) {
                return new NimbusLookAndFeel();
            } // KDE
            else if (OSDetector.isKDE()) {
                // return new GTKLookAndFeel();
            }
        } else if (OSDetector.isMac()) {
            return new NimbusLookAndFeel();
        }
        return new NimbusLookAndFeel();
    }

    /**
     * Método utilitário que configura o JFileChooser
     */
    public static void configJFileChooser() {
        UIManager.put("FileChooser.lookInLabelText", "Local:");
        UIManager.put("FileChooser.lookInLabelMnemonic", "o");
        UIManager.put("FileChooser.fileNameLabelText", "Nome do arquivo:");
        UIManager.put("FileChooser.fileNameLabelMnemonic", "N");
        UIManager.put("FileChooser.filesOfTypeLabelText", "Arquivos do tipo:");
        UIManager.put("FileChooser.filesOfTypeLabelMnemonic", "t");
        UIManager.put("FileChooser.upFolderToolTipText", "Um Nível Acima");
        UIManager.put("FileChooser.upFolderAccessibleName", "Para Cima");
        UIManager.put("FileChooser.homeFolderToolTipText", "Inicio");
        UIManager.put("FileChooser.homeFolderAccessibleName", "Inicio");
        UIManager.put("FileChooser.newFolderToolTipText", "Criar uma Nova Pasta");
        UIManager.put("FileChooser.newFolderAccessibleName", "Nova Pasta");
        UIManager.put("FileChooser.listViewButtonToolTipText", "Lista");
        UIManager.put("FileChooser.listViewButtonAccessibleName", "Lista");
        UIManager.put("FileChooser.detailsViewButtonToolTipText", "Detalhes");
        UIManager.put("FileChooser.detailsViewButtonAccessibleName", "Detalhes");
        UIManager.put("FileChooser.cancelButtonText", "Cancelar");
        UIManager.put("FileChooser.cancelButtonMnemonic", "C");
        UIManager.put("FileChooser.cancelButtonToolTipText", "Cancelar");
        UIManager.put("FileChooser.openButtonText", "Abrir");
        UIManager.put("FileChooser.openButtonToolTipText", "Abrir");
        UIManager.put("FileChooser.openButtonAccessibleName", "Abrir");
        UIManager.put("FileChooser.openButtonMnemonic", "A");
        UIManager.put("FileChooser.saveButtonText", "Salvar");
        UIManager.put("FileChooser.saveButtonToolTipText", "Salvar Arquivo");
    }

    /**
     * Collects all supported LAFs for the current system
     *
     * @author JDownloader
     * @return
     */
    public static LAFWrapper[] getSupportedLookAndFeels() {
        LookAndFeelInfo[] lafis = UIManager.getInstalledLookAndFeels();

        ArrayList<LAFWrapper> ret = new ArrayList<LAFWrapper>();
        for (int i = 0; i < lafis.length; i++) {
            String clname = lafis[i].getClassName();

            if (clname.contains("Substance") && JavaInfo.getJavaVersion() >= 1.6) {
                ret.add(new LAFWrapper(lafis[i]).setName(
                        lafis[i].getName().replaceAll("([A-Z])", " $0").trim()));
            } else if (clname.contains("Synthetica") && !clname.contains("SyntheticaStandard")) {
                ret.add(new LAFWrapper(lafis[i]).setName(
                        lafis[i].getName().replaceAll("([A-Z])", " $0").trim()));
            } else if (clname.contains("goodie")) {
                LAFWrapper lafm = new LAFWrapper(lafis[i]);
                lafm.setName(lafis[i].getName());
                ret.add(lafm);
            } else if (clname.startsWith("apple.laf")) {
                LAFWrapper lafm = new LAFWrapper(lafis[i]);
                lafm.setName("Apple Aqua");
                ret.add(lafm);
            } else if (clname.endsWith("WindowsLookAndFeel")) {
                LAFWrapper lafm = new LAFWrapper(lafis[i]);
                lafm.setName("Windows Style");
                ret.add(lafm);
            } else if (clname.endsWith("MetalLookAndFeel") && OSDetector.isLinux()) {
                LAFWrapper lafm = new LAFWrapper(lafis[i]);
                lafm.setName("Light(Metal)");
                ret.add(lafm);
            } else if (clname.endsWith("GTKLookAndFeel") && OSDetector.isLinux()) {
                LAFWrapper lafm = new LAFWrapper(lafis[i]);
                lafm.setName("Light(GTK)");
                ret.add(lafm);
            } else if (clname.startsWith("com.jtattoo")) {
                LAFWrapper lafm = new LAFWrapper(lafis[i]);
                lafm.setName(lafis[i].getName());
                ret.add(lafm);
            }
        }
        return ret.toArray(new LAFWrapper[]{});
    }
}
