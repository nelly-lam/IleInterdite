import model.Island;
import views.View;
import views.ViewMenu;

//TODO Ajouter des interfaces pour les classes

public class Main {
    public static void main (String[] args) {
        Island model = new Island(15, 15);
        //View vue = new View(model);
        ViewMenu viewMenu = new ViewMenu(model);
    }
}
