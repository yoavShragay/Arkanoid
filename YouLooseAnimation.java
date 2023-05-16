import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type You loose animation.
 */
public class YouLooseAnimation implements Animation {
    private final KeyboardSensor keyboard;
    private final boolean stop;
    private final int score;

    /**
     * Instantiates a new You loose animation.
     *
     * @param k     the k
     * @param score the score
     */
    public YouLooseAnimation(KeyboardSensor k, int score) {
        this.score = score;
        this.keyboard = k;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(150, d.getHeight() / 2, "Game Over. Your score is " + score, 32);

    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
