package vaucangraph.components.filechooser;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import utils.file.FileUtils;

/**
 * JFileChooser personalizado Vaucangraph
 * @author Kleber Kruger
 */
public class JVFileChooser extends JFileChooser {

    public static final String DESCRIPTION_VCG = "VCG (*.vcg)";
    public static final String DESCRIPTION_VCP = "Vaucanson project (*.vcp)";
    public static final String DESCRIPTION_PNG = "Imagem PNG (*.png)";
    public static final String DESCRIPTION_JPG = "Imagem JPG (*.jpg)";
    public static final String EXTENSION_VCG = "vcg";
    public static final String EXTENSION_TXT = "vcp";
    public static final String EXTENSION_PNG = "png";
    public static final String EXTENSION_JPG = "jpg";
    /**
     * Tipo da janela
     */
    public int dialogType;
    /**
     * Extensão do JFileChooser
     */
    private String extension;

    /**
     * Cria um JFileChooser personalizado Vaucangraph
     * @param type
     * @param description
     * @param extension
     */
    public JVFileChooser(int type, String extension) {
        // Chama superclasse inicializando o file chooser
        super();
        this.dialogType = type;
        this.extension = extension;

        // Define o diretório atual e adiciona o filtro
        setCurrentDirectory(new File(getDefaultFolder(extension)));
        addDefaultFilter(extension);

        if (type == JVFileChooser.OPEN_DIALOG) {
            setDialogType(JVFileChooser.OPEN_DIALOG);
        }
        if (type == JVFileChooser.SAVE_DIALOG) {
            setDialogType(JVFileChooser.SAVE_DIALOG);
        }
    }

    private void addDefaultFilter(String extension) {
        if (extension.equalsIgnoreCase(EXTENSION_VCG)) {
            FileFilter filter = new ExtensionFileFilter(DESCRIPTION_VCG, EXTENSION_VCG);
            setFileFilter(filter);
        }
        if (extension.equalsIgnoreCase(EXTENSION_TXT)) {
            FileFilter filter = new ExtensionFileFilter(DESCRIPTION_VCP, EXTENSION_TXT);
            setFileFilter(filter);
        }
        if (extension.equalsIgnoreCase(EXTENSION_PNG)) {
            FileFilter filter = new ExtensionFileFilter(DESCRIPTION_PNG, EXTENSION_PNG);
            setFileFilter(filter);
        }
    }

    private String getDefaultFolder(String extension) {
        if (extension.equalsIgnoreCase(EXTENSION_VCG)) {
            return FileUtils.getPathProjectFolder();
        }
        return FileUtils.getPathMyDocuments();
    }

    @Override
    public void approveSelection() {

        File selectedFile = getSelectedFile();
        if (!selectedFile.getName().endsWith("." + extension)) {
            selectedFile = new File(selectedFile.getPath() + "." + extension);
            setSelectedFile(selectedFile);
        }

        if (dialogType == JVFileChooser.SAVE_DIALOG) {
            // Se o arquivo já existir, cria um JDialog de aviso
            if (selectedFile.exists()) {
                Object[] options = {"Sim", "Não"};
                int option = JOptionPane.showOptionDialog(this,
                        "O arquivo " + selectedFile.getName() + " já existe, " + "\n"
                        + "deseja substituí-lo?", "Vaucangraph 2.0",
                        JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION,
                        null, options, options[0]);

                if (option == JOptionPane.YES_OPTION) {
                    super.approveSelection();
                }

            } else {
                super.approveSelection();
            }

        } else {
            super.approveSelection();
        }
    }
}
