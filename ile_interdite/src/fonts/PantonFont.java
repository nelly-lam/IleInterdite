package fonts;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PantonFont {

    private PantonFont() { }

    public static Font getPanton() throws IOException, FontFormatException {
        InputStream filePanton = new FileInputStream("src/fonts/Panton-Regular.ttf");
        return Font.createFont(Font.TRUETYPE_FONT, filePanton);
    }

    public static Font getPantonLight() throws IOException, FontFormatException {
        InputStream filePantonLight = new FileInputStream("src/fonts/Panton-Light.ttf");
        return Font.createFont(Font.TRUETYPE_FONT, filePantonLight);
    }

    public static Font getPantonBold() throws IOException, FontFormatException {
        InputStream filePantonBold = new FileInputStream("src/fonts/Panton-Bold.ttf");
        return Font.createFont(Font.TRUETYPE_FONT, filePantonBold);
    }
}
