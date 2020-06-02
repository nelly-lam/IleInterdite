package main;

import model.Island;
import views.ViewMenu;

public class Main {
    public static void main (String[] args) {
        Island model = new Island(12, 12);
        new ViewMenu(model);
    }
}