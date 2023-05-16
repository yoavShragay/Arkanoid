import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * @author Yoav Shragay <\yoavshra@gmail.com> 318730066s
 */
public class FlickerStar implements Sprite {

    public static final int GAME_WIDTH = 800;
    public static final int GAME_HIGHT = 600;
    private final Point anker;
    private final int size;
    private final int flicker;
    private final Color background;
    private boolean flag;
    private int count;

    /**
     * Instantiates a new Flicker star.
     *
     * @param background the background
     */
    public FlickerStar(Color background) {
        this.background = background;
        Random rand = new Random();
        this.anker = new Point(rand.nextInt(GAME_WIDTH), rand.nextInt(GAME_HIGHT));
        this.size = rand.nextInt(2) + 1;
        this.flicker = rand.nextInt(10) + 10;
        flag = true;
        count = 0;

    }

    @Override
    public void drawOn(DrawSurface d) {
        if (flag) {
            d.setColor(Color.lightGray);
        } else {
            d.setColor(Color.black);
        }
        d.fillCircle((int) anker.getX(), (int) anker.getY(), size);

    }

    @Override
    public void timePassed() {
        if (this.anker.getY() == GAME_HIGHT) {
            this.anker.setY(0);
        }
        count++;
        if (count % flicker == 0) {
            flag = !flag;
            if (count == 1000000) {
                this.count = 0;
            }
        }

    }

    @Override
    public void addToGame(GameLevel g) {

    }
}