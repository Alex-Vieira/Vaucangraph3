package jgraph.model;

import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultGraphCell;

/**
 * Implementação de uma célula personalizada Vaucangraph
 * @author Kleber Kruger
 */
public class vGraphCell extends DefaultGraphCell {

    /**
     * Nó do inspector 
     */
    protected transient vMutableTreeNode node;
    /**
     * Flag que indica quando o inspector precisa ser atualizado
     */
    protected transient boolean nameChanged;

    /**
     * Cria uma célula vazia
     */
    public vGraphCell() {
        super();
    }

    /**
     * Cria uma vGraphCell com um userObject
     * @param userObject
     */
    public vGraphCell(Object userObject) {
        super(userObject);
    }

    /**
     * Cria uma vGraphCell com um userObject e um AttributeMap
     * @param userObject
     * @param attributeMap
     */
    public vGraphCell(Object userObject, AttributeMap attributeMap) {
        super(userObject, attributeMap);
    }

    /**
     * Cria uma vGraphCell com um userObject, um AttributeMap e um conjunto de filhos
     * @param userObject
     * @param attributeMap
     * @param children
     */
    public vGraphCell(Object userObject, AttributeMap attributeMap,
            vMutableTreeNode[] children) {

        super(userObject, attributeMap, children);
    }

    @Override
    public void setUserObject(Object userObject) {
        super.setUserObject(userObject);
        getInspectorNode().setUserObject(userObject);
    }

    @Override
    public String toString() {
        return userObject.toString();
    }

    /**
     * @return o nó do inspector
     */
    public vMutableTreeNode getInspectorNode() {
        if (node == null) {
            node = new vMutableTreeNode(this);
        }
        return node;
    }

    /**
     * @return the node
     */
    public vMutableTreeNode getNode() {
        return node;
    }

    /**
     * @param node the node to set
     */
    public void setNode(vMutableTreeNode node) {
        this.node = node;
    }

    /**
     * @return the name changed
     */
    public boolean isNameChanged() {
        return nameChanged;
    }

    /**
     * @param nameChanged the nameChanged to set
     */
    public void setNameChanged(boolean nameChanged) {
        this.nameChanged = nameChanged;
    }
}
