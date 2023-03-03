import javax.swing.*;

/**
 * A Java based active rendering framework for real time software rendering.
 */
public class Application {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Window window = new Window(640, 480, new NoiseScene(), 24);
        });
    }

}

