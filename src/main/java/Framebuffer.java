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

    public void setPixel(int x, int y, int texel) {
        colorBuffer[x + y * 320] = texel;
    }

    public void blendPixel(int x, int y, int texel) {
        float alpha = (float) Math.abs(Math.sin(System.currentTimeMillis() * 0.001));
        colorBuffer[x + y * 320] = mixTexel(colorBuffer[x + y * 320], texel, alpha);
    }

    public int mixTexel(int old, int texel, float malpha) {
        int oldred = (old >> 16) & 0xff;
        int oldgreen = (old >> 8) & 0xff;
        int oldblue = (old >> 0) & 0xff;
        int texa = (texel >> 24) & 0xff;
        int texred = (texel >> 16) & 0xff;
        int texgreen = (texel >> 8) & 0xff;
        int texblue = (texel >> 0) & 0xff;
        float alpha = (float) (texa / 255 * malpha);
        int green = (int) (oldgreen * (1 - alpha) + texgreen * alpha);
        int blue = (int) (oldblue * (1 - alpha) + texblue * alpha);
        int red = (int) (oldred * (1 - alpha) + texred * alpha);
        return (blue & 0xff) << 0 | (green & 0xff) << 8 | (red & 0xff) << 16;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

}
