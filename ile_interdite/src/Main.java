import model.Island;
import views.ViewGame;
import views.ViewMenu;

import javax.swing.text.View;
import java.awt.*;
import java.io.IOException;

//TODO Ajouter des interfaces pour les classes

public class Main {
    public static void main (String[] args) throws IOException, FontFormatException {
        Island model = new Island(12, 12);
        ViewGame vue = new ViewGame(model);
        //ViewMenu viewMenu = new ViewMenu(model);
    }
}
