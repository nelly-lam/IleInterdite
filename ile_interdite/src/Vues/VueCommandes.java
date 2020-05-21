package Vues;

import InterfaceGraphique.Controleur;
import InterfaceGraphique.Modele;
import javax.swing.*;
import java.awt.event.ActionListener;

public class VueCommandes extends JPanel {
    private final Modele modele;

    public VueCommandes(Modele modele) {
        this.modele = modele;
        JButton boutonAvance = new JButton("fin de tour");
        this.add(boutonAvance);
        Controleur ctrl = new Controleur(this.modele);
        boutonAvance.addActionListener(ctrl);
    }
}
