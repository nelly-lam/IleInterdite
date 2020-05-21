package InterfaceGraphique;

import Vues.Vue;

public class IleInterdite {
    public static void main(String[] args) {
        Modele modele = new Modele();
        Vue vue = new Vue(modele);
    }
}
