import javax.swing.*;
import java.awt.*;

public class View {
    private final Model model;
    private JFrame frame;
    private ViewGrid grid;
    private ViewCommand command;

    public View(Model model) {
        this.model = model;
        this.frame = new JFrame();
        this.frame.setTitle("L'île interdite");
        this.frame.setLayout(new FlowLayout());
        this.grid = new ViewGrid(this.model);
        this.frame.add(this.grid);
        this.command = new ViewCommand(this.model);
        this.frame.add(this.command);
        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }

}
