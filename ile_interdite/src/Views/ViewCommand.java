package Views;

import javax.swing.*;

public class ViewCommand extends JPanel {
    private final Model model;

    public ViewCommand(Model model) {
        this.model = model;
        JButton button = new JButton("fin de tour");
        this.add(button);
        Controller ctrl = new Controller(this.model);
        button.addActionListener(ctrl);
    }
}
