import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * @author Yoav Shragay <\yoavshra@gmail.com> 318730066s
 */
public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private final int countFrom;
    private final SpriteCollection gameScreen;
    private final Sleeper sleeper = new Sleeper();
    private boolean stop;


    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {


        if (countFrom != 3) {
            this.sleeper.sleepFor((long) numOfSeconds * 1000 / 3);
        }
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.red);
        d.drawText(400, 300, String.valueOf(countFrom), 32);

        this.stop = true;


    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
