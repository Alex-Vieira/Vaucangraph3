package vaucangraph.components.popupmenu;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import jgraph.view.Canvas;
import vaucansong.bean.State;
import vaucansong.bean.Transition;

/**
 * Menu popup do canvas
 * @author Kleber Kruger
 */
public class JVPopupMenu extends JPopupMenu {

    /**
     * Ícones do menu popup:
     */
    // Desfazer
    private final ImageIcon undoIcon = new ImageIcon(getClass().getResource(
            "/vaucangraph/icons/popup-undo.png"));
    // Refazer
    private final ImageIcon redoIcon = new ImageIcon(getClass().getResource(
            "/vaucangraph/icons/popup-redo.png"));
    // Recortar
    private final ImageIcon cutIcon = new ImageIcon(getClass().getResource(
            "/vaucangraph/icons/popup-cut.png"));
    // Copiar
    private final ImageIcon copyIcon = new ImageIcon(getClass().getResource(
            "/vaucangraph/icons/popup-copy.png"));
    // Colar
    private final ImageIcon pasteIcon = new ImageIcon(getClass().getResource(
            "/vaucangraph/icons/popup-paste.png"));
    // Estado
    private final ImageIcon stateIcon = new ImageIcon(getClass().getResource(
            "/vaucangraph/icons/popup-state.png"));
    // Transição
    private final ImageIcon transitionIcon = new ImageIcon(getClass().getResource(
            "/vaucangraph/icons/popup-transition.png"));
    // Deletar
    private final ImageIcon deleteIcon = new ImageIcon(getClass().getResource(
            "/vaucangraph/icons/popup-delete.png"));

    public JVPopupMenu() {
        super();
    }

    public JVPopupMenu(Canvas canvas, Object selected) {
        super();
        addItens(canvas, selected);
    }

    /**
     * Adiciona itens ao menu popup de acordo com o objeto selecionado
     *
     * @param canvas
     * o canvas
     *
     * @param selected
     * o objeto selecionado
     */
    private void addItens(Canvas canvas, Object selected) {

        if (selected instanceof State) {

            addRefresh(canvas); // Adiciona o item atualizar no menu popup
            addSeparator(); // Adiciona separador

            addUndo(canvas); // Adiciona o item desfazer no menu popup
            addRedo(canvas); // Adiciona o item refazer no menu popup
            addSeparator(); // Adiciona separador

            addCut(canvas); // Adiciona o item recortar no menu popup
            addCopy(canvas); // Adiciona o item copiar no menu popup
            addSeparator(); // Adiciona separador

            addInsert(canvas); // Adiciona o item inserir no menu popup
            addRename(canvas); // Adiciona o item editar no menu popup
            addDelete(canvas); // Adiciona o item remover no menu popup

        } else if (selected instanceof Transition) {

            addRefresh(canvas); // Adiciona o item atualizar no menu popup
            addSeparator(); // Adiciona separador

            addUndo(canvas); // Adiciona o item desfazer no menu popup
            addRedo(canvas); // Adiciona o item refazer no menu popup
            addSeparator(); // Adiciona separador

            addCut(canvas); // Adiciona o item recortar no menu popup
            addCopy(canvas); // Adiciona o item copiar no menu popup
            addPaste(canvas); // Adiciona o item colar no menu popup
            addSeparator(); // Adiciona separador

            addInsert(canvas); // Adiciona o item inserir no menu popup
            addRename(canvas); // Adiciona o item editar no menu popup
            addDelete(canvas); // Adiciona o item remover no menu popup

        } else {
            // Adiciona componentes de quando o usuário clica em uma área vazia
            addRefresh(canvas);
            addSeparator(); // Adiciona separador

            addUndo(canvas); // Adiciona o item desfazer no menu popup
            addRedo(canvas); // Adiciona o item refazer no menu popup
            addSeparator(); // Adiciona separador

            addPaste(canvas); // Adiciona o item colar no menu popup
            addSeparator(); // Adiciona separador

            addInsert(canvas); // Adiciona o item inserir no menu popup
        }
    }

    public void addRefresh(final Canvas canvas) {
        JMenuItem item = new JMenuItem(new AbstractAction("Atualizar") {

            public void actionPerformed(ActionEvent e) {
                canvas.refresh();
            }
        });
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        add(item);
    }

    public void addUndo(final Canvas canvas) {
        JMenuItem item = new JMenuItem(new AbstractAction("Desfazer") {

            public void actionPerformed(ActionEvent e) {
                canvas.undo();
            }
        });
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
        item.setIcon(undoIcon);
        item.setEnabled(canvas.getUndoManager().canUndo());
        add(item);
    }

    public void addRedo(final Canvas canvas) {
        JMenuItem item = new JMenuItem(new AbstractAction("Refazer") {

            public void actionPerformed(ActionEvent e) {
                canvas.redo();
            }
        });

        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
        item.setIcon(redoIcon);
        item.setEnabled(canvas.getUndoManager().canRedo());
        add(item);
    }

    public void addCut(final Canvas canvas) {
        JMenuItem item = new JMenuItem(new AbstractAction("Recortar") {

            public void actionPerformed(ActionEvent e) {
                canvas.cut(e);
            }
        });
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        item.setIcon(cutIcon);
        add(item);
    }

    public void addCopy(final Canvas canvas) {
        JMenuItem item = new JMenuItem(new AbstractAction("Copiar") {

            public void actionPerformed(ActionEvent e) {
                canvas.copy(e);
            }
        });
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        item.setIcon(copyIcon);
        add(item);
    }

    public void addPaste(final Canvas canvas) {
        JMenuItem item = new JMenuItem(new AbstractAction("Colar") {

            public void actionPerformed(ActionEvent e) {
                canvas.paste(e);
            }
        });
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        item.setIcon(pasteIcon);
        add(item);
    }

    public void addInsert(final Canvas canvas) {
        JMenu menu = new JMenu("Inserir");

        JMenuItem stateMenu = new JMenuItem(new AbstractAction("Estado") {

            public void actionPerformed(ActionEvent e) {
                //canvas.insertState();
            }
        });
        stateMenu.setIcon(stateIcon);
        stateMenu.setEnabled(true);

        JMenuItem transitionMenu = new JMenuItem(new AbstractAction("Transição") {

            public void actionPerformed(ActionEvent e) {
                //canvas.insertTransition();
            }
        });
        transitionMenu.setIcon(transitionIcon);
        transitionMenu.setEnabled(true);

        menu.add(stateMenu);
        menu.add(transitionMenu);

        add(menu);
        //popupMenu.setEnabled(true);
    }

    public void addRename(final Canvas canvas) {
        JMenuItem item = new JMenuItem(new AbstractAction("Renomear") {

            public void actionPerformed(ActionEvent e) {
                canvas.startEditingAtCell(canvas.getSelectionCell());
            }
        });
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        add(item);
    }

    public void addDelete(final Canvas canvas) {
        JMenuItem item = new JMenuItem(new AbstractAction("Excluir") {

            public void actionPerformed(ActionEvent e) {
                canvas.removeCells(canvas.getSelectionCells());
            }
        });
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        item.setIcon(deleteIcon);
        add(item);
    }
}
