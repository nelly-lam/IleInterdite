import java.util.ArrayList;

public abstract class Observable {
    private ArrayList<Observer> observateurs;

    public Observable() {
        this.observateurs = new ArrayList<Observer>();
    }

    public void addObserver(Observer o) {
        this.observateurs.add(o);
    }

    public void notifyObservers() {
        for (Observer o : this.observateurs) {
            o.update();
        }
    }
}
