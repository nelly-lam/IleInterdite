package InterfaceGraphique;

import java.util.ArrayList;

abstract class Observable {
    private ArrayList<Observateur> observateurs;

    public Observable() {
        this.observateurs = new ArrayList<Observateur>();
    }

    public void addObserver(Observateur o) {
        this.observateurs.add(o);
    }

    public void notifyObservers() {
        for (Observateur o : this.observateurs) {
            o.update();
        }
    }
}
