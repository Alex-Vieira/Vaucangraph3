/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vaucangraph.model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.Serializable;
import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import utils.laf.LAFManager;

/**
 *
 * @author Kleber Kruger
 */
public class Style implements Serializable {

    private LookAndFeel theme;
    private Dimension size;
    private Point position;
    private int extendedState;

    public Style() {

        theme = LAFManager.getDefaultLookAndFeel();

        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 150;
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 150;
        size = new Dimension(width, height);

        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        position = new Point((screenSize.width - width) / 2, (screenSize.height - height) / 2);

        extendedState = JFrame.NORMAL;
    }

    /**
     * @return the theme
     */
    public LookAndFeel getTheme() {
        return theme;
    }

    /**
     * @param theme the theme to set
     */
    public void setTheme(LookAndFeel theme) {
        this.theme = theme;
    }

    /**
     * @return the size
     */
    public Dimension getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Dimension size) {
        this.size = size;
    }

    /**
     * @return the position
     */
    public Point getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * @return the extendedState
     */
    public int getExtendedState() {
        return extendedState;
    }

    /**
     * @param extendedState the extendedState to set
     */
    public void setExtendedState(int extendedState) {
        this.extendedState = extendedState;
    }
}
