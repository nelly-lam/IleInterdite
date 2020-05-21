import Model.Island;
import Views.View;

public class Main {
    public static void main (String[] args) {
        Island model = new Island(20, 20);
        View vue = new View(model);
    }
}
