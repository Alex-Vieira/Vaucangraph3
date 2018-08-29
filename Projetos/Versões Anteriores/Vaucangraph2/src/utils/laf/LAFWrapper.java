/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.laf;

import java.io.Serializable;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author Kleber Kruger
 */
public class LAFWrapper implements Serializable {

    private String className;
    private String name;

    public LAFWrapper(LookAndFeelInfo lafi) {
        this.className = lafi.getClassName();
        this.name = lafi.getName();
    }

    public LAFWrapper(String className) {
        this.className = className;
        name = className.substring(className.lastIndexOf(".") + 1);
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public LAFWrapper setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof LAFWrapper) && ((LAFWrapper) obj).getClassName() != null && ((LAFWrapper) obj).getClassName().equals(className);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + (this.className != null ? this.className.hashCode() : 0);
        hash = 13 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return name;
    }
}
