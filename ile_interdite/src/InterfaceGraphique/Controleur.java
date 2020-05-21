package InterfaceGraphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controleur implements ActionListener {
    private final Modele modele;

    public Controleur(Modele modele) {
        this.modele = modele;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        modele.monteeDesEaux();
    }
}
