package fonts;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PantonFont {

    private PantonFont() { }

    public static Font getPanton() {
        try {
            FileInputStream filePanton = new FileInputStream("src/fonts/Panton-Regular.ttf");
            return Font.createFont(Font.TRUETYPE_FONT, filePanton);
        } catch (FontFormatException | IOException e) {
            return new Font("Arial", Font.PLAIN, 15);
        }
    }

    public static Font getPantonLight() {
        try {
            InputStream filePantonLight = new FileInputStream("src/fonts/Panton-Light.ttf");
            return Font.createFont(Font.TRUETYPE_FONT, filePantonLight);
        } catch (FontFormatException | IOException e) {
            return new Font("Arial", Font.PLAIN, 15);
        }
    }

    public static Font getPantonBold() {
        try {
            FileInputStream filePantonBold = new FileInputStream("src/fonts/Panton-Bold.ttf");
            return Font.createFont(Font.TRUETYPE_FONT, filePantonBold);
        } catch (FontFormatException | IOException e) {
            return new Font("Arial", Font.BOLD, 15);
        }
    }
}