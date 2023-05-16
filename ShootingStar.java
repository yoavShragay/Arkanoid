import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * The type Shooting star.
 */
public class ShootingStar implements Sprite {
    /**
     * The constant GAME_WIDTH.
     */
    public static final int GAME_WIDTH = 800;
    /**
     * The constant GAME_HIGHT.
     */
    public static final int GAME_HIGHT = 600;
    private final Point anker;
    private boolean flag;
    private final int speed;
    private int count;
    private final int size;
    private final int flicker;
    private final Color background;

    /**
     * Instantiates a new Shooting star.
     *
     * @param background the background
     */
    public ShootingStar(Color background) {
        this.background = background;
        Random rand = new Random();
        this.anker = new Point(rand.nextInt(GAME_WIDTH), rand.nextInt(GAME_HIGHT));
        this.size = rand.nextInt(2) + 1;
        this.flicker = rand.nextInt(10) + 10;
        this.speed = rand.nextInt(3) + 1;
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
        this.anker.setY(this.anker.getY() + speed);
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
