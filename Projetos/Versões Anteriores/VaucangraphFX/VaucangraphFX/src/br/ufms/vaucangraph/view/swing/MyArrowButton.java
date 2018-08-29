/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.vaucangraph.view.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.UIResource;

/**
 * JButton object that draws a scaled arrow in one of the cardinal directions.
 * <p>
 * <strong>Warning:</strong>
 * Serialized objects of this class will not be compatible with future Swing releases. The current serialization support
 * is appropriate for short term storage or RMI between applications running the same version of Swing. As of 1.4,
 * support for long term storage of all JavaBeans&trade; has been added to the <code>java.beans</code> package. Please
 * see {@link java.beans.XMLEncoder}.
 *
 * @author David Kloba
 */
public final class MyArrowButton extends JButton implements SwingConstants {

    /**
     * The direction of the arrow. One of {@code SwingConstants.NORTH}, {@code SwingConstants.SOUTH},
     * {@code SwingConstants.EAST} or {@code SwingConstants.WEST}.
     */
    protected int direction;

    protected Color arrowColor;
    protected Color borderColor;
    protected int borderSize;

    /**
     * Creates a {@code BasicArrowButton} whose arrow is drawn in the specified direction.
     *
     * @param direction the direction of the arrow; one of {@code SwingConstants.NORTH}, {@code SwingConstants.SOUTH},
     * {@code SwingConstants.EAST} or {@code SwingConstants.WEST}
     */
    public MyArrowButton(int direction) {
        this(direction, UIManager.getColor("control"), UIManager.getColor("controlDkShadow"));
    }

    /**
     * Creates a {@code BasicArrowButton} whose arrow is drawn in the specified direction and with the specified colors.
     *
     * @param direction the direction of the arrow; one of {@code SwingConstants.NORTH}, {@code SwingConstants.SOUTH},
     * {@code SwingConstants.EAST} or {@code SwingConstants.WEST}
     * @param backgroundColor the background color of the button
     * @param arrowColor the color of the arrow
     */
    public MyArrowButton(int direction, Color backgroundColor, Color arrowColor) {
        this(direction, backgroundColor, arrowColor, null, 0);
    }

    /**
     * Creates a {@code BasicArrowButton} whose arrow is drawn in the specified direction and with the specified colors.
     *
     * @param direction the direction of the arrow; one of {@code SwingConstants.NORTH}, {@code SwingConstants.SOUTH},
     * {@code SwingConstants.EAST} or {@code SwingConstants.WEST}
     * @param backgroundColor the background color of the button
     * @param arrowColor the color of the arrow
     * @param borderColor the color of the border
     * @param borderSize the size of the border
     */
    public MyArrowButton(int direction, Color backgroundColor, Color arrowColor, Color borderColor, int borderSize) {
        super();
        this.arrowColor = arrowColor;
        this.borderColor = borderColor;
        this.borderSize = borderSize; // FIXME: To check the value informed

        setRequestFocusEnabled(false);
        setDirection(direction);
        setBackground(backgroundColor);
    }

    /**
     * Returns the direction of the arrow.
     *
     * @return
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Sets the direction of the arrow.
     *
     * @param direction the direction of the arrow; one of of {@code SwingConstants.NORTH},
     * {@code SwingConstants.SOUTH}, {@code SwingConstants.EAST} or {@code SwingConstants.WEST}
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public void paint(Graphics g) {

        int w = getSize().width;
        int h = getSize().height;
        boolean isPressed = getModel().isPressed();
        Color origColor = g.getColor();

        g.setColor(isPressed ? getBackground().darker() : getBackground());
        g.fillRect(0, 0, w, h);

        // Draw the proper Border
        if (getBorder() != null && !(getBorder() instanceof UIResource)) {
            paintBorder(g);
        } else if (borderSize > 0) {
            g.setColor(isPressed ? borderColor.darker() : borderColor);
            /**
             * FIXME: Set border size
             */
            for (int i = 0; i < borderSize; i++) {
                g.drawRect(0 + i, 0 + i, w - (i * 2), h - (i * 2));
            }
        }

        // If there's no room to draw arrow, bail
        if (h < 5 || w < 5) {
            g.setColor(origColor);
            return;
        }

        g.translate(1, 1);

        // Draw the arrow
        int size = Math.min((h - 4) / 3, (w - 4) / 3);
        size = Math.max(size, 2);
        paintTriangle(g, (w - size) / 2, (h - size) / 2,
                size, direction, isPressed, isEnabled());

        // Reset the Graphics back to it's original settings
        g.translate(-1, -1);

        g.setColor(origColor);
    }

    /**
     * Returns the preferred size of the {@code BasicArrowButton}.
     *
     * @return the preferred size
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(16, 16);
    }

    /**
     * Returns the minimum size of the {@code BasicArrowButton}.
     *
     * @return the minimum size
     */
    @Override
    public Dimension getMinimumSize() {
        return new Dimension(5, 5);
    }

    /**
     * Returns the maximum size of the {@code BasicArrowButton}.
     *
     * @return the maximum size
     */
    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Returns whether the arrow button should get the focus. {@code BasicArrowButton}s are used as a child component of
     * composite components such as {@code JScrollBar} and {@code JComboBox}. Since the composite component typically
     * gets the focus, this method is overriden to return {@code false}.
     *
     * @return {@code false}
     */
    @Override
    @Deprecated
    public boolean isFocusTraversable() {
        return false;
    }

    /**
     * Paints a triangle.
     *
     * @param g the {@code Graphics} to draw to
     * @param x the x coordinate
     * @param y the y coordinate
     * @param size the size of the triangle to draw
     * @param direction the direction in which to draw the arrow; one of {@code SwingConstants.NORTH},
     * {@code SwingConstants.SOUTH}, {@code SwingConstants.EAST} or {@code SwingConstants.WEST}
     * @param isPressed whether or not the button is pressed
     * @param isEnabled whether or not the arrow is drawn enabled
     */
    public void paintTriangle(Graphics g, int x, int y, int size, int direction, boolean isPressed, boolean isEnabled) {

        Color oldColor = g.getColor();
        Color color = isPressed ? this.arrowColor.darker() : this.arrowColor;

        size = Math.max(size, 2);
        int mid = (size / 2) - 1;
        int i, j = 0;

        g.translate(x, y);

        /* FIXME: This if-else is not necessary */
        if (isEnabled) {
            g.setColor(color);
        } else {
            g.setColor(color);
        }

        switch (direction) {
            case NORTH:
                for (i = 0; i < size; i++) {
                    g.drawLine(mid - i, i, mid + i, i);
                }
                if (!isEnabled) {
                    g.setColor(color);
                    g.drawLine(mid - i + 2, i, mid + i, i);
                }
                break;
            case SOUTH:
                if (!isEnabled) {
                    g.translate(1, 1);
                    g.setColor(color);
                    for (i = size - 1; i >= 0; i--) {
                        g.drawLine(mid - i, j, mid + i, j);
                        j++;
                    }
                    g.translate(-1, -1);
                    g.setColor(color);
                }

                j = 0;
                for (i = size - 1; i >= 0; i--) {
                    g.drawLine(mid - i, j, mid + i, j);
                    j++;
                }
                break;
            case WEST:
                for (i = 0; i < size; i++) {
                    g.drawLine(i, mid - i, i, mid + i);
                }
                if (!isEnabled) {
                    g.setColor(color);
                    g.drawLine(i, mid - i + 2, i, mid + i);
                }
                break;
            case EAST:
                if (!isEnabled) {
                    g.translate(1, 1);
                    g.setColor(color);
                    for (i = size - 1; i >= 0; i--) {
                        g.drawLine(j, mid - i, j, mid + i);
                        j++;
                    }
                    g.translate(-1, -1);
                    g.setColor(color);
                }

                j = 0;
                for (i = size - 1; i >= 0; i--) {
                    g.drawLine(j, mid - i, j, mid + i);
                    j++;
                }
                break;
        }
        g.translate(-x, -y);
        g.setColor(oldColor);
    }
}
