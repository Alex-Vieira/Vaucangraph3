package utils.file;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import vaucangraph.model.Style;

/**
 * Classe que possui métodos utilitários para trabalhar com arquivos
 * @author Kleber Kruger
 */
public class FileUtils {

    /**
     * Atributos que retornam informações sobre o nome do estado
     */
    public static final int FILENAME_EMPTY = 0;
    public static final int FILENAME_TOO_LARGE = 1;
    public static final int FILENAME_INVALID_CHARACTERS = 2;
    public static final int FILENAME_EXISTS = 3;
    public static final int FILENAME_OK = 4;

    /**
     * Método utilitário que cria uma nova pasta
     *
     * @param path endereço da pasta
     * @return um file referente à pasta criada
     */
    public static File createNewFolder(String path) {
        File file = new File(path);
        file.mkdir();
        return file;
    }

    /**
     * Método utilitário que cria um novo arquivo
     *
     * @param path endereço do arquivo
     * @return um file referente ao arquivo criado
     */
    public static File createNewFile(String path) {
        File file = null;
        try {
            file = new File(path);
            file.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
            file = null;
        }
        return file;
    }

    public static int fileNameIsValid(String path, String fileName) {

        String extension = ".vcg";
        if (fileName.endsWith(".vcg")) {
            extension = "";
        }

        // Instancia um objeto do tipo arquivo
        File file = new File(path + File.separator + fileName + extension);

        // Verifica se o nome do arquivo está vazio
        if (fileName.trim().isEmpty()) {
            return FILENAME_EMPTY;
        }

        // Verifica se o nome do arquivo não é muito grande
        if (fileName.length() > 150) {
            return FILENAME_TOO_LARGE;
        }

        // Verifica se o nome do arquivo possui caractéres inválidos
        if (fileName.contains("\\") || fileName.contains("/") || fileName.contains(":")
                || fileName.contains("*") || fileName.contains("?") || fileName.contains("\"")
                || fileName.contains("<") || fileName.contains(">") || fileName.contains("|")) {

            return FILENAME_INVALID_CHARACTERS;
        }

        // Verifica se já não existe um arquivo com esse nome
        if (file.exists()) {
            return FILENAME_EXISTS;
        }

        // Se passou em todos os testes, retorna verdadeiro
        return FILENAME_OK;
    }

    /**
     * Método utilitário que adiciona extensão ao arquivo caso necessite
     * @param file o arquivo
     *
     * @return
     * o arquivo com extensão (nome do arquivo + extensão)
     */
    public static String addExtension(File file) {
        return addExtension(file.getName());
    }

    /**
     * Método utilitário que adiciona extensão ao arquivo caso necessite
     * @param fileName
     * o nome do arquivo
     *
     * @return
     * o nome do arquivo + extensão (.vcg)
     */
    public static String addExtension(String fileName) {
        if (fileName.trim().length() < 5) {
            return fileName + ".vcg";
        }
        if (fileName.substring(fileName.length() - 4, fileName.length()).
                equalsIgnoreCase(".vcg")) {
            return fileName;
        }
        return fileName + ".vcg";
    }

    /**
     * Método utilitário que cria um novo arquivo de configuração de estilo
     * @return o arquivo de configuração style.xml
     */
    public static Style createStyleXML() {
        Style style = new Style();

        createNewFolder(getPathConfigurationFolder());
        saveFile(style, getStyleFile());

        return style;
    }

    /**
     * Método utilitário para escrever uma String em um arquivo
     *
     * @param file arquivo para escrita
     * @param string conteúdo que será escrito no arquivo
     * @return um booleano que indica se a operação foi realizada com sucesso
     */
    public static boolean writeInFile(File file, String string) {
        try {
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw, true);
            pw.println(string);

            return true;

        } catch (IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Método utilitário que lê o conteúdo de um arquivo
     *
     * @param file arquivo para leitura
     * @return um booleano que indica se a operação foi realizada com sucesso
     */
    public static String readFile(File file) {
        String string = null;
        if (file.exists()) {
            try {
                FileReader reader = new FileReader(file);
                BufferedReader buffReader = new BufferedReader(reader);
                string = buffReader.readLine();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return string;
    }

    /**
     * Método utilitário que lê o conteúdo de um arquivo
     *
     * @param path endereço do arquivo para leitura
     * @return um booleano que indica se a operação foi realizada com sucesso
     */
    public static String readFile(String path) {
        File file = new File(path);
        String string = null;
        if (file.exists()) {
            try {
                FileReader reader = new FileReader(file);
                BufferedReader buffReader = new BufferedReader(reader);
                string = buffReader.readLine();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return string;
    }

    /**
     * Método utilitário que lê o objeto serializado no arquivo recebido como
     * parâmetro
     * @param file o arquivo que contém o objeto serializado
     * @return o objeto
     */
    public static Object openFile(File file) {
        try {
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
                    new FileInputStream(file)));

            return decoder.readObject();

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo " + file.getPath(),
                    "FileNotFoundException", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    /**
     * Método utilitário que serializa e salva o objeto no arquivo recebido como
     * parâmetro
     * @param file o arquivo que receberá as informações do objeto serializado
     * @return um booleano que indica verdadeiro quando o objeto for lido
     * (deserializado)
     */
    public static boolean saveFile(Serializable object, File file) {
        try {
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
                    new FileOutputStream(file)));

            encoder.writeObject(object);
            encoder.close();

            return true;

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o arquivo " + file.getPath(),
                    "FileNotFoundException", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    /**
     * @return o file contendo a pasta de configuração do programa
     */
    public static File getConfigurationFolder() {
        File configurationFolder = new File(getPathConfigurationFolder());
        if (configurationFolder.exists()) {
            return configurationFolder;
        }
        return null;
    }

    /**
     * @return o arquivo de configuração de estilo do programa
     */
    public static File getStyleFile() {
        File styleFile = new File(getPathStyleFile());
        if (styleFile.exists()) {
            return styleFile;
        }

        createNewFolder(getPathConfigurationFolder());
        saveFile(new Style(), createNewFile(getPathStyleFile()));

        return getStyleFile();
    }

    /**
     * @return o Style (objeto) de configuração de estilo do programa
     */
    public static Style getStyleXML() {
        File styleFile = new File(getPathStyleFile());
        if (!styleFile.exists()) {
            createNewFolder(getPathConfigurationFolder());
            saveFile(new Style(), createNewFile(getPathStyleFile()));
        }

        return (Style) openFile(styleFile);
    }

    /**
     * @return a pasta padrão de projetos
     */
    public static File getProjectFolder() {
        File projectFolder = new File(getPathProjectFolder());
        if (!projectFolder.exists()) {
            createNewFolder(getPathProjectFolder());
        }

        return projectFolder;
    }

    /**
     * @return o endereço da pasta de configuração do programa
     */
    public static String getPathConfigurationFolder() {
        return System.getProperty("user.home") + File.separator + ".vaucangraph";
    }

    /**
     * @return o endereço do arquivo de configuração de estilo
     */
    public static String getPathStyleFile() {
        return System.getProperty("user.home") + File.separator + ".vaucangraph" + File.separator + "style.xml";
    }

    /**
     * @return o endereço da pasta de projetos
     */
    public static String getPathMyDocuments() {
        return new JFileChooser().getCurrentDirectory() + "";
    }

    /**
     * @return o endereço da pasta de projetos
     */
    public static String getPathProjectFolder() {
        //return ShellFolder.get("fileChooserDefaultFolder") + File.separator + "Vaucangraph";
        return new JFileChooser().getCurrentDirectory() + File.separator + "Vaucangraph";
    }
}
