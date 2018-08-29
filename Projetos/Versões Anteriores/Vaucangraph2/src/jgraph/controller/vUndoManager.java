package jgraph.controller;

import javax.swing.event.UndoableEditEvent;
import jgraph.view.Canvas;
import org.jgraph.graph.GraphUndoManager;
import vaucangraph.view.Vaucangraph;

/**
 * Gerenciador de ações desfazer/refazer personalizado Vaucangraph
 * @author Kleber Kruger
 */
public class vUndoManager extends GraphUndoManager {

    private final Vaucangraph vcg;
    private final Canvas canvas;

    public vUndoManager(Canvas canvas) {

        super();

        this.canvas = canvas;
        this.vcg = canvas.getProjectPanel().getVaucangraph();
    }

    @Override
    public void undoableEditHappened(UndoableEditEvent e) {
        super.undoableEditHappened(e);
        updateUndoRedo();

        canvas.getProject().setSaved(false);
        vcg.getJbSave().setEnabled(!canvas.getProject().isSaved());
    }

    /**
     * Método que habilita/desabilita os botões desfazer/refazer
     * @param bln
     */
    public void updateUndoRedo() {
        vcg.getJmiUndo().setEnabled(canUndo(canvas.getGraphLayoutCache()));
        vcg.getJmiRedo().setEnabled(canRedo(canvas.getGraphLayoutCache()));
        vcg.getJbUndo().setEnabled(canUndo(canvas.getGraphLayoutCache()));
        vcg.getJbRedo().setEnabled(canRedo(canvas.getGraphLayoutCache()));
    }
}
