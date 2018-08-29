package jgraph.model;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import vaucansong.bean.State;
import vaucansong.bean.Transition;

/**
 *
 * @author Kleber Kruger
 */
public class vMutableTreeNode extends DefaultMutableTreeNode {

    /**
     * Célula do nó
     */
    protected vGraphCell cell;

    /**
     * Cria um nó vazio
     */
    public vMutableTreeNode() {
        cell = null;
    }

    /**
     * Cria um nó para o inspector
     * @param cell
     */
    public vMutableTreeNode(vGraphCell cell) {
        super(cell.toString());
        this.cell = cell;
    }

    @Override
    public void setUserObject(Object userObject) {
        if (!userObject.toString().equals(getUserObject().toString())) {
            if (cell != null) {

                if (cell instanceof State) {
                    // Altera o label do nó
                    super.setUserObject(userObject);

                    State state = (State) cell;
                    state.setUserObject(userObject);
                    cell.setNameChanged(true);
                }
                if (cell instanceof Transition) {
                    // Altera o label do nó
                    super.setUserObject(userObject);

                    Transition transition = (Transition) cell;
                    transition.setUserObject(userObject);
                    cell.setNameChanged(true);
                }

            } else {
                super.setUserObject(userObject);
            }
        }

    }

    @Override
    public String toString() {
        if (cell != null) {
            cell.toString();
        }
        return super.toString();
    }

    /**
     * @return the cell
     */
    public vGraphCell getCell() {
        return cell;
    }

    /**
     * @param cell the cell to set
     */
    public void setCell(vGraphCell cell) {
        this.cell = cell;
    }

    /**
     * @return the node path
     */
    public TreePath getNodePath() {
        TreeNode[] treeNodes = this.getPath();
        return new TreePath(treeNodes);
    }
}
