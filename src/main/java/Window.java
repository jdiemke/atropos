import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;

class Window extends JFrame {

    private static final long serialVersionUID = 1L;

    Framebuffer framebuffer;
    Insets insets;
    Scene scene;

    public Window(int width, int height, Scene scene, int fps) {
        this.scene = scene;
        setTitle("Java Software Rasterizer");

        setIgnoreRepaint(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        setResizable(false);
        getContentPane().setPreferredSize(new Dimension(width, height));
        pack();
        setVisible(true);

        createBufferStrategy(2);

        insets = getInsets();
        BufferStrategy bufferStrategy = getBufferStrategy();

        framebuffer = new Framebuffer();

        scene.init();

        new Timer().scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                Graphics2D graphics2D = null;
                try {
                    graphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
                    scene.render(framebuffer);
                    graphics2D.drawImage(framebuffer.getBufferedImage(), insets.left, insets.top, 640, 480, null);
                    bufferStrategy.show();
                } catch (Exception ex) {
                    if (graphics2D != null) {
                        graphics2D.dispose();
                    }
                    System.err.println(ex);
                }
            }

        }, 0, 1000 / fps);
    }

}
