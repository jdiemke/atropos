public class NoiseScene implements Scene {

    Texture texture;

    public void init() {
        this.texture = ImageUtils.load("razor1911.png");
    }

    public void render(Framebuffer framebuffer) {
        drawTelevisionNoise(framebuffer);

        for (int x = 0; x < texture.width; x++) {
            for (int y = 0; y < texture.height; y++) {
                int texel = texture.texels[x + y * texture.width];
                framebuffer.blendPixel(x, y, texel);
            }
        }
    }

    private void drawTelevisionNoise(Framebuffer framebuffer) {
        for (int i = 0; i < 320 * 240; i++) {
            framebuffer.setPixel(i, 0, 20, (int) (Math.random() * 255), (int) (Math.random() * 25));
        }
    }

}
