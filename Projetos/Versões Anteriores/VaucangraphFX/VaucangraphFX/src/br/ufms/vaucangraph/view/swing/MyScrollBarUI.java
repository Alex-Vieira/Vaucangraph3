/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.vaucangraph.view.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 *
 * @author kleberkruger
 */
public class MyScrollBarUI extends BasicScrollBarUI {

    private final JButton upButton = new MyArrowButton(SwingConstants.NORTH, Color.DARK_GRAY, Color.GRAY);
    private final JButton downButton = new MyArrowButton(SwingConstants.SOUTH, Color.DARK_GRAY, Color.GRAY);
    private final JButton leftButton = new MyArrowButton(SwingConstants.WEST, Color.DARK_GRAY, Color.GRAY);
    private final JButton rightButton = new MyArrowButton(SwingConstants.EAST, Color.DARK_GRAY, Color.GRAY);

    private JButton createNoneButton() {

        JButton button = new MyArrowButton(0);
        Dimension zeroDim = new Dimension(0, 0);

        button.setPreferredSize(zeroDim);
        button.setMinimumSize(zeroDim);
        button.setMaximumSize(zeroDim);

        return button;
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return orientation == SwingConstants.WEST ? leftButton : upButton;
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
//        return createNoneButton();
        return orientation == SwingConstants.EAST ? rightButton : downButton;
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {

        thumbColor = Color.DARK_GRAY;

        g.setColor(thumbColor);
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);

        if (trackHighlight == DECREASE_HIGHLIGHT) {
            paintDecreaseHighlight(g);
        } else if (trackHighlight == INCREASE_HIGHLIGHT) {
            paintIncreaseHighlight(g);
        }
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
            return;
        }

        int w = thumbBounds.width;
        int h = thumbBounds.height;

        g.translate(thumbBounds.x, thumbBounds.y);

        thumbColor = Color.GRAY;

//        scrollBarWidth = 10; // Scroll width
//        System.out.println(scrollBarWidth);
        // Draw the border
//        g.setColor(thumbDarkShadowColor);
//        drawRect(g, 0, 0, w - 1, h - 1);
        g.setColor(thumbColor);

        switch (scrollbar.getOrientation()) {
            case JScrollBar.VERTICAL:
                g.fillRect(4, 0, w - 8, h - 1);
                break;
            case JScrollBar.HORIZONTAL:
                g.fillRect(0, 4, w - 1, h - 8);
                break;
            default:
                g.fillRect(0, 0, w - 1, h - 1);
        }

        g.translate(-thumbBounds.x, -thumbBounds.y);
    }
}
