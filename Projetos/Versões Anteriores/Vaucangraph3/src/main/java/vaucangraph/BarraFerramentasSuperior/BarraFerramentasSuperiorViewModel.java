package vaucangraph.BarraFerramentasSuperior;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

public class BarraFerramentasSuperiorViewModel implements ViewModel {

    /*
    private ObjectProperty BTNnovo = new SimpleObjectProperty();
    private ObjectProperty BTNabrir = new SimpleObjectProperty();
    private ObjectProperty BTNsalvar = new SimpleObjectProperty();
    private ObjectProperty BTNzoomMais = new SimpleObjectProperty();
    private ObjectProperty BTNzoomMenos = new SimpleObjectProperty();
     
     */
    private StringProperty TFHorizontal = new SimpleStringProperty("");
    private StringProperty TFVertical = new SimpleStringProperty("");
    private ListProperty ComboZoom = new SimpleListProperty();
    BarraFerramentasSuperiorModel bfsm;
    public ListProperty ComboZoom(){
       ComboZoom.add(bfsm.populaComboBox());
        return ComboZoom;
    }

    public StringProperty TFHorizontal() {
        return TFHorizontal;
    }

    public StringProperty TFVertical() {
        return TFVertical;
    }

    public ListProperty getComboZoom() {
        return ComboZoom;
    }

    public void setComboZoom(ListProperty ComboZoom) {
        this.ComboZoom = ComboZoom;
    }



    public StringProperty getTFHorizontal() {
        return TFHorizontal;
    }

    public void setTFHorizontal(StringProperty TFHorizontal) {
        this.TFHorizontal = TFHorizontal;
    }

    public StringProperty getTFVertical() {
        return TFVertical;
    }

    public void setTFVertical(StringProperty TFVertical) {
        this.TFVertical = TFVertical;
    }

    
}
