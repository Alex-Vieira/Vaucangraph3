/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaucangraph.BarraFerramentasSuperior;

import javafx.collections.ObservableList;

/**
 *
 * @author admin
 */
public class BarraFerramentasSuperiorModel {

    ObservableList listaZoom = null;

    public ObservableList populaComboBox() {

        listaZoom.add("400%");
        listaZoom.add("200%");
        listaZoom.add("150%");
        listaZoom.add("100%");
        listaZoom.add("75%");
        listaZoom.add("50%");

        listaZoom.add("Fit window");
        listaZoom.add("Fit page");
        listaZoom.add("Fit page width");
        listaZoom.add("Two pages");
        return listaZoom;
    }

}
