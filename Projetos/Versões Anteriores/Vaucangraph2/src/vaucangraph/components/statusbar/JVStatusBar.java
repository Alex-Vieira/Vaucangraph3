package vaucangraph.components.statusbar;

import vaucangraph.model.enumeration.ProjectType;

/**
 * Barra de status personalizada vaucangraph
 * @author Kleber Kruger
 */
public class JVStatusBar extends org.jdesktop.swingx.JXStatusBar {

    private javax.swing.JLabel jlMousePosition;
    private javax.swing.JLabel jlStateSize;
    private javax.swing.JLabel jlCanvasSize;
    private javax.swing.JLabel jlProjectType;

    /**
     * Barra de status Vaucangraph
     */
    public JVStatusBar(ProjectType projectType) {
        super();
        initComponents(projectType);
    }

    /** Método que inicializa e adiciona todos os componentes da barra de status */
    private void initComponents(ProjectType projectType) {

        jlMousePosition = new javax.swing.JLabel();
        jlStateSize = new javax.swing.JLabel();
        jlCanvasSize = new javax.swing.JLabel();
        jlProjectType = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(300, 28));

        jlMousePosition.setIcon(new javax.swing.ImageIcon(getClass().getResource(
                "/vaucangraph/icons/move.png")));
        jlMousePosition.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlMousePosition.setPreferredSize(
                new java.awt.Dimension(150, jlMousePosition.getPreferredSize().height));
        jlMousePosition.setToolTipText("<html>\n<b>Posição do mouse</b><br>\n"
                + "Indica as coordenadas x,y do mouse sobre o canvas\n</html>");
        add(jlMousePosition);

        jlStateSize.setIcon(new javax.swing.ImageIcon(getClass().getResource(
                "/vaucangraph/icons/statesize.png")));
        jlStateSize.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlStateSize.setPreferredSize(
                new java.awt.Dimension(150, jlStateSize.getPreferredSize().height));
        jlStateSize.setToolTipText("<html>\n<b>Tamanho do estado</b><br>\n"
                + "Tamanho do estado selecionado\n</html>");
        add(jlStateSize);

        jlCanvasSize.setIcon(new javax.swing.ImageIcon(getClass().getResource(
                "/vaucangraph/icons/resize.png")));
        jlCanvasSize.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlCanvasSize.setPreferredSize(
                new java.awt.Dimension(150, jlCanvasSize.getPreferredSize().height));
        jlCanvasSize.setToolTipText("<html>\n<b>Tamanho do canvas</b><br>\n"
                + "Tamanho da área de desenho do projeto\n</html>");
        add(jlCanvasSize);

        javax.swing.JLabel label = new javax.swing.JLabel();
        add(label, org.jdesktop.swingx.JXStatusBar.Constraint.ResizeBehavior.FILL);

        if (projectType != null) {
            jlProjectType.setText(projectType.toString());
        }
        jlProjectType.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlProjectType.setToolTipText("<html>\n<b>Tipo do projeto</b><br>\n"
                + "Tipo do projeto atual\n</html>");
        add(jlProjectType);
    }

    /**
     * @return the jlMousePosition
     */
    public javax.swing.JLabel getJlMousePosition() {
        return jlMousePosition;
    }

    /**
     * @return the jlStateSize
     */
    public javax.swing.JLabel getJlStateSize() {
        return jlStateSize;
    }

    /**
     * @return the jlCanvasSize
     */
    public javax.swing.JLabel getJlCanvasSize() {
        return jlCanvasSize;
    }

    /**
     * @return the jlProjectType
     */
    public javax.swing.JLabel getJlProjectType() {
        return jlProjectType;
    }
}
