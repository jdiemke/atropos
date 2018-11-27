import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

public class ImageUtils {

    public static Texture load(String image) {
        BufferedImage img = null;

        try {
            img = ImageIO.read(ImageUtils.class.getResourceAsStream((image)));
        } catch (IOException e) {
            System.out.println(e);
        }

        BufferedImage tmp = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        tmp.getGraphics().drawImage(img, 0, 0, null);

        int[] texture = ((DataBufferInt) tmp.getRaster().getDataBuffer()).getData();
        return new Texture(img.getWidth(), img.getHeight(), texture);
    }

    private ImageUtils() {
    }

}
