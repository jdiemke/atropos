import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Framebuffer {

    private BufferedImage bufferedImage;
    private int[] colorBuffer;

    public Framebuffer() {
        bufferedImage = new BufferedImage(320, 240, BufferedImage.TYPE_INT_RGB);
        colorBuffer = ((DataBufferInt) bufferedImage.getRaster().getDataBuffer()).getData();
    }

    public void setPixel(int x, int y, int red, int green, int blue) {
        colorBuffer[x + y * 320] = (blue & 0xff) << 0 | (green & 0xff) << 8 | (red & 0xff) << 16;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

}
