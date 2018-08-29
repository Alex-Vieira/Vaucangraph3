package vaucansong.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JComponent;
import utils.state.StateUtils;
import vaucansong.bean.State;
import vaucansong.bean.enumeration.StateSize;

/**
 * Implementação da visão de um vértice ou estado no Vaucangraph 2.0
 *
 * <br><br><br>
 * Vaucangraph 2 - Ferramenta para desenho de autômatos e grafos <br><br>
 * @version 2.0
 *
 * @author Kleber Kruger
 * @author José Ademar Peixoto de Souza
 */
public class StateView extends JComponent {

    private final State state;
    private Dimension dimension;
    private int widthArrow;
    /**
     * Default Colors
     * Cores padrão do estado:
     **/
    // Cores padrão para o preenchimento da célula:
    private Color defaultColor = new Color(255, 255, 255); // Branco - White
    private Color focusedColor = new Color(235, 250, 250); // Azul claro - Light Blue
    private Color focusedDimmedColor = new Color(215, 215, 215); // Cinza claro - Light Gray
    private Color selectedColor = new Color(153, 204, 255); // Azul - Blue
    private Color selectedDimmedColor = new Color(192, 192, 192); // Cinza - Gray
    // Cores padrão para a borda da céula:
    private Color defaultBorderColor = new Color(0, 0, 0); // Preto - Black
    private Color selectedBorderColor = new Color(0, 102, 153); // Azul Escuro - Navy
    private Color dimmedBorderColor = new Color(153, 153, 153); // Cinza - Gray
    private Color selectedDimmedBorderColor = new Color(102, 102, 102); // Cinza - Gray

    /**
     * Cria uma visão do estado - StateView
     *
     * @param state
     * Estado mapeado pela StateView
     */
    public StateView(State state) {
        this.state = state;

        dimension = (Dimension) StateUtils.getStateSize(this.state);
        widthArrow = StateUtils.getWidthArrow(this.state);

        initComponents();
    }

    private void initComponents() {
        setSize(dimension); // Altera o tamanho do componente
        setBounds(new Rectangle(dimension)); // Altera o tamanho das bordas
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Adiciona o antialiased.
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Célula oculta
        if (!state.isHidden()) {

            // Se a célula estiver selecionada:
            if (state.isSelected()) {
                if (state.isDimmed()) {
                    //Se o estado estiver esmaecido
                    g2d.setPaint(selectedDimmedColor);
                } // Cor de seleção padrão
                else {
                    g2d.setPaint(selectedColor);
                }
            } // Senão, se a célula somente estiver em focus ou for preview:
            else if (state.isFocused() || state.isPreview()) {
                // Se o estado estiver esmaecido
                if (state.isDimmed()) {
                    g2d.setPaint(focusedDimmedColor);
                } // Cor de focus padrão
                else {
                    g2d.setPaint(focusedColor);
                }
            } // Cor padrão:
            else {
                g2d.setPaint(defaultColor);
            }
            // Preenche a elipse
            g2d.fill(new Ellipse2D.Double(widthArrow, 0, getWidth() - widthArrow, getHeight()));


            // Se a célula estiver selecionada:
            if (state.isSelected()) {
                // Se estiver esmaecido
                if (state.isDimmed()) {
                    g2d.setPaint(selectedDimmedBorderColor);
                } // Cor da borda de seleção padrão
                else {
                    g2d.setPaint(selectedBorderColor);
                }
            } // Senão, se o estado estiver esmaecido
            else if (state.isDimmed()) {
                g2d.setPaint(dimmedBorderColor);
            } // Senão, se a célula somente estiver em focus ou for preview:
            else if (state.isFocused() || state.isPreview()) {
                g2d.setPaint(selectedBorderColor);
            } // Cor padrão:
            else {
                g2d.setPaint(defaultBorderColor);
            }

            // Desenha a borda do estado
            g2d.draw(new Ellipse2D.Double(widthArrow, 0, getWidth() - (widthArrow + 1), getHeight() - 1));

            if (state.isInitial()) {
                g2d.draw(new Line2D.Double(0, getHeight() / 2, widthArrow, getHeight() / 2));
                g2d.draw(new Line2D.Double(widthArrow - widthArrow * 0.4, (getHeight() / 2)
                        - widthArrow * 0.4, widthArrow, getHeight() / 2));
                g2d.draw(new Line2D.Double(widthArrow - widthArrow * 0.4, (getHeight() / 2)
                        + widthArrow * 0.4, widthArrow, getHeight() / 2));
            }

            if (state.isFinal()) {
                if (state.getSize() == StateSize.LARGE) {
                    g2d.draw(new Ellipse2D.Double(widthArrow + 5, 5, getWidth() - (widthArrow + 11), getHeight() - 11));
                } else {
                    g2d.draw(new Ellipse2D.Double(widthArrow + 3, 3, getWidth() - (widthArrow + 7), getHeight() - 7));
                }
            }

            FontMetrics fm = g2d.getFontMetrics();
            int width = (int) StateUtils.getStateSize(false, state.getSize()).getWidth();
            g2d.drawString(state.getName(), widthArrow + (width / 2) - (fm.stringWidth(state.getName()) / 2),
                    (getHeight() / 2) + (fm.getHeight() / 3));
        }

    }

    /**
     * @return a largura da flecha
     */
    public int getWidthArrow() {
        return widthArrow;
    }

    // Métodos Getter/Setter que alteram as propriedades //
    @Override
    public String getName() {
        return state.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        state.setName(name);
    }

    @Override
    public String toString() {
        return state.getName();
    }
}
