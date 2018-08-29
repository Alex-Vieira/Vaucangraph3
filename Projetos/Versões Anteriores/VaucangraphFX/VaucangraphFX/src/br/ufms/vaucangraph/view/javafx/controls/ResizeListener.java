/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.vaucangraph.view.javafx.controls;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author kleberkruger
 */
public class ResizeListener implements EventHandler<MouseEvent> {

    private double dx;
    private double dy;
    private double deltaX;
    private double deltaY;
    private boolean moveH;
    private boolean moveV;
    private boolean resizeH = false;
    private boolean resizeV = false;
    private final double border = 10;

    @Override
    public void handle(MouseEvent e) {

        Object obj = e.getSource();

        final Scene scene = (obj instanceof Scene) ? (Scene) obj
                : (obj instanceof Node) ? ((Node) obj).getScene() : null;

        if (scene != null && (obj = scene.getWindow()) instanceof Stage) {

            final Stage stage = (Stage) obj;

            double eX = e.getX();
            double eY = e.getY();
            double width = scene.getWidth();
            double height = scene.getHeight();

            if (MouseEvent.MOUSE_MOVED.equals(e.getEventType())) {
                if (eX < border && eY < border) {
                    scene.setCursor(Cursor.NW_RESIZE);
                    resizeH = true;
                    resizeV = true;
                    moveH = true;
                    moveV = true;
                } else if (eX < border && eY > height - border) {
                    scene.setCursor(Cursor.SW_RESIZE);
                    resizeH = true;
                    resizeV = true;
                    moveH = true;
                    moveV = false;
                } else if (eX > width - border && eY < border) {
                    scene.setCursor(Cursor.NE_RESIZE);
                    resizeH = true;
                    resizeV = true;
                    moveH = false;
                    moveV = true;
                } else if (eX > width - border && eY > height - border) {
                    scene.setCursor(Cursor.SE_RESIZE);
                    resizeH = true;
                    resizeV = true;
                    moveH = false;
                    moveV = false;
                } else if (eX < border || eX > width - border) {
                    scene.setCursor(Cursor.H_RESIZE);
                    resizeH = true;
                    resizeV = false;
                    moveH = (eX < border);
                    moveV = false;
                } else if (eY < border || eY > height - border) {
                    scene.setCursor(Cursor.V_RESIZE);
                    resizeH = false;
                    resizeV = true;
                    moveH = false;
                    moveV = (eY < border);
                } else {
                    scene.setCursor(Cursor.DEFAULT);
                    resizeH = false;
                    resizeV = false;
                    moveH = false;
                    moveV = false;
                }
            } else if (MouseEvent.MOUSE_PRESSED.equals(e.getEventType())) {
                dx = stage.getWidth() - eX;
                dy = stage.getHeight() - eY;
            } else if (MouseEvent.MOUSE_DRAGGED.equals(e.getEventType())) {
                if (resizeH) {
                    double stageWidth = stage.getWidth();
                    if (stageWidth <= stage.getMinWidth()) {
                        if (moveH) {
                            deltaX = stage.getX() - e.getScreenX();
                            if (eX < 0) {// if new > old, it's permitted
                                stage.setWidth(deltaX + stageWidth);
                                stage.setX(e.getScreenX());
                            }
                        } else {
                            if (eX + dx - stageWidth > 0) {
                                stage.setWidth(eX + dx);
                            }
                        }
                    } else if (stageWidth > stage.getMinWidth()) {
                        if (moveH) {
                            deltaX = stage.getX() - e.getScreenX();
                            stage.setWidth(deltaX + stageWidth);
                            stage.setX(e.getScreenX());
                        } else {
                            stage.setWidth(eX + dx);
                        }
                    }
                }
                if (resizeV) {
                    double stageHeight = stage.getHeight();
                    if (stageHeight <= stage.getMinHeight()) {
                        if (moveV) {
                            deltaY = stage.getY() - e.getScreenY();
                            if (eY < 0) { // if new > old, it's permitted
                                stage.setHeight(deltaY + stageHeight);
                                stage.setY(e.getScreenY());
                            }
                        } else {
                            if (eY + dy - stageHeight > 0) {
                                stage.setHeight(eX + dy);
                            }
                        }
                    } else if (stageHeight > stage.getMinHeight()) {
                        if (moveV) {
                            deltaY = stage.getY() - e.getScreenY();
                            stage.setHeight(deltaY + stageHeight);
                            stage.setY(e.getScreenY());
                        } else {
                            stage.setHeight(eY + dy);
                        }
                    }
                }
            } else if (MouseEvent.MOUSE_ENTERED.equals(e.getEventType())) {
                if (!e.isPrimaryButtonDown()) {
                    scene.setCursor(Cursor.DEFAULT);
                }
            } else if (MouseEvent.MOUSE_EXITED.equals(e.getEventType())) {
                if (!e.isPrimaryButtonDown()) {
                    scene.setCursor(Cursor.DEFAULT);
                }
            }
        }
    }
}
