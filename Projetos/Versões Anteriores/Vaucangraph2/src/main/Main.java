package main;

import java.lang.reflect.InvocationTargetException;
import utils.file.FileUtils;
import utils.laf.LAFManager;
import utils.system.OSDetector;
import vaucangraph.model.Style;
import vaucangraph.view.SplashScreen;
import vaucangraph.view.Vaucangraph;

/**
 * Classe que inicializa e configura o Vaucangraph <br><br><br>
 *
 * <b>Vaucangraph 2</b> - A evolução de uma ferramenta para desenho de autômatos
 * e grafos com geração de códigos Vaucanson-G <br><br>
 *
 * Desenvolvida por: <br><br>
 * Kleber Kruger <br>
 * José Ademar Peixoto de Souza <br>
 *
 * @version 2.0
 *
 * @author Kleber Kruger
 * @author José Ademar Peixoto de Souza
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
            throws InterruptedException, InvocationTargetException {

        // Cria o splash de apresentação do programa
        SplashScreen splash = new SplashScreen();
        // Cria a pasta padrão de projetos
        FileUtils.createNewFolder(FileUtils.getPathProjectFolder());

        // Tempo de duração do splash
        //Thread.sleep(1000);

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                // Inicia o Vaucangraph 2.0
                new Vaucangraph(getStyle()).setVisible(true);
            }
        });

        splash.close();
    }

    /**
     * Método que obtém o estilo (tema, dimensão, posicionamento e estado da janela)
     * do Frame Principal (Vaucangraph)
     *
     * @return o Style (objeto de configuração do estilo) do Vaucangraph
     */
    private static Style getStyle() {

        // Altera o tema da barra de título:
        if (OSDetector.getOSID() == OSDetector.OS_WINDOWS_VISTA
                || OSDetector.getOSID() == OSDetector.OS_WINDOWS_7) {

            // Barra de título decorada pelo sistema
            LAFManager.setDefaultLAFDecorated(false);

        } else {
            //Barra de título decorada pelo look and feel
            LAFManager.setDefaultLAFDecorated(true);
        }

        // Style recebe o objeto serializado no arquivo style.xml da pasta de configuração
        Style style = (Style) FileUtils.getStyleXML();

        LAFManager.setLookAndFeel(style.getTheme());
        LAFManager.configJFileChooser();

        return style;
    }
}
