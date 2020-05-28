import model.Island;
import views.ViewMenu;

//TODO Ajouter des interfaces pour les classes

public class Main {
    public static void main (String[] args) {
        Island model = new Island(20, 20);
        //View vue = new View(model);
        ViewMenu viewMenu = new ViewMenu(model);
    }
}
