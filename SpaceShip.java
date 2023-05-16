import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Space ship.
 */
public class SpaceShip implements Sprite {
    /**
     * The constant GAME_WIDTH.
     */
    public static final int GAME_WIDTH = 800;
    /**
     * The constant GAME_HIGHT.
     */
    public static final int GAME_HIGHT = 600;
    private static final int BORDER_WIDTH = 20;
    private final Point anker;
    private int speed;
    private final int size;
    private final Color shipColor;

    /**
     * Instantiates a new Space ship.
     *
     * @param x         the x
     * @param y         the y
     * @param shipColor the ship color
     * @param size      the size
     * @param speed     the speed
     */
    public SpaceShip(double x, double y, Color shipColor, int size, int speed) {
        anker = new Point(x, y);
        this.shipColor = shipColor;
        this.size = size;
        //Random rand = new Random();
        this.speed = speed;
    }

    /**
     * Move x.
     *
     * @param x the x
     */
    public void moveX(int x) {
        anker.setX(anker.getX() + x);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(shipColor);
        d.fillOval((int) (anker.getX()), (int) anker.getY(), size, (int) (0.6 * size));
        d.setColor(Color.blue);
        d.drawOval((int) (anker.getX() + size / 6), (int) anker.getY() - 25, (int) (size * 0.66),
                (int) (0.6 * (size * 0.8)));
        d.setColor(new Color(137, 207, 240));
        d.fillOval((int) (anker.getX() + size / 6), (int) anker.getY() - 25, (int) (size * 0.66),
                (int) (0.6 * (size * 0.8)));
        d.setColor(Color.lightGray);
        //d.fillOval((int)(anker.getX()+size/6), (int)(anker.getY()-12.5),(int)(size*0.5),(int)(0.6*(size*0.5625)));
        d.setColor(new Color(160, 175, 183));
        d.fillOval((int) (anker.getX() + size / 8), (int) (anker.getY() + size / 3.5), size / 10, size / 10);
        d.fillOval((int) (anker.getX() + size / 3), (int) (anker.getY() + size / 2.6), size / 10, size / 10);
        d.fillOval((int) (anker.getX() + size / 1.7), (int) (anker.getY() + size / 2.6), size / 10, size / 10);
        d.fillOval((int) (anker.getX() + size / 1.25), (int) (anker.getY() + size / 3.5), size / 10, size / 10);


        //d.fillCircle((int)(anker.getX()+size/4),(int)anker.getY(),size/15);

    }

    @Override
    public void timePassed() {
        if (anker.getX() > GAME_WIDTH - size - BORDER_WIDTH) {
            this.speed = -Math.abs(speed);
        } else if (anker.getX() < BORDER_WIDTH) {
            this.speed = Math.abs(speed);
        }
        anker.setX(anker.getX() + speed);
    }

    @Override
    public void addToGame(GameLevel g) {

    }
}
