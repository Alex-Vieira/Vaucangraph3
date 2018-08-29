package vaucangraph.BarraFerramentasLateral;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class BarraFerramentasLateralViewModel implements ViewModel {

    private final BooleanProperty TBSelecao = new SimpleBooleanProperty();
    private final BooleanProperty TBEstadoPequeno = new SimpleBooleanProperty();
    private final BooleanProperty TBEstadoMedio = new SimpleBooleanProperty();
    private final BooleanProperty TBEstadoGrande = new SimpleBooleanProperty();

    public BooleanProperty getTBSelecao() {
        return TBSelecao;
    }

    public BooleanProperty getTBEstadoPequeno() {
        return TBEstadoPequeno;
    }

    public BooleanProperty getTBEstadoMedio() {
        return TBEstadoMedio;
    }

    public BooleanProperty getTBEstadoGrande() {
        return TBEstadoGrande;
    }

}
