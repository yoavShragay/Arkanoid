import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type You win animation.
 */
public class YouWinAnimation implements Animation {
    private final KeyboardSensor keyboard;
    private final boolean stop;
    private final int score;

    /**
     * Instantiates a new You win animation.
     *
     * @param k     the k
     * @param score the score
     */
    public YouWinAnimation(KeyboardSensor k, int score) {
        this.score = score;
        this.keyboard = k;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(150, d.getHeight() / 2, "You Win! Your score is " + score, 32);

    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
