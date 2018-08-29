package vaucangraph.view;

import java.lang.reflect.InvocationTargetException;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;

/**
 *
 * @author Kleber Kruger
 */
public class SplashScreen extends JDialog {

    public SplashScreen() throws InterruptedException {
        super();
        initComponents();
    }

    public void close() throws InterruptedException, InvocationTargetException {
        Runnable closerRunner = new Runnable() {

            public void run() {
                setVisible(false);
                dispose();
            }
        };

        SwingUtilities.invokeAndWait(closerRunner);
    }

    private void initComponents() {

        jpBackground = new javax.swing.JPanel();
        jlSplash = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Abrindo Vaucangraph...");
        setResizable(false);
        setUndecorated(true);

        jlSplash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vaucangraph/icons/splash.jpg"))); // NOI18N

        javax.swing.GroupLayout jpBackgroundLayout = new javax.swing.GroupLayout(jpBackground);
        jpBackground.setLayout(jpBackgroundLayout);
        jpBackgroundLayout.setHorizontalGroup(
                jpBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jlSplash));
        jpBackgroundLayout.setVerticalGroup(
                jpBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jlSplash));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jpBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jpBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 350) / 2, (screenSize.height - 247) / 2, 350, 247);

        setVisible(true);
    }
    // Variables declaration - do not modify
    private javax.swing.JLabel jlSplash;
    private javax.swing.JPanel jpBackground;
    // End of variables declaration
}
