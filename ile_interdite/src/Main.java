import model.Island;
import views.ViewMenu;

import java.awt.*;
import java.io.IOException;

//TODO Ajouter des interfaces pour les classes

public class Main {
    public static void main (String[] args) throws IOException, FontFormatException {
        Island model = new Island(12, 12);
        ViewMenu viewMenu = new ViewMenu(model);
    }
}
