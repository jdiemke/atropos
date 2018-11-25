public class NoiseScene implements Scene{

    public void render(Framebuffer framebuffer) {
        for (int i = 0; i < 320 * 240; i++) {
            framebuffer.setPixel(i, 0, 20, (int) (Math.random() * 255),  (int)(Math.random() * 25));
        }
    }

}
