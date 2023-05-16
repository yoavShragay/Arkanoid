import biuoop.DrawSurface;

import java.awt.Polygon;
import java.awt.Color;

/**
 * The type Tree.
 */
public class Tree implements Sprite {
    private Polygon triangle1;
    private Polygon triangle2;
    private Polygon triangle3;
    private final Point ankor;

    /**
     * Instantiates a new Tree.
     *
     * @param x the x
     * @param y the y
     */
    public Tree(double x, double y) {
        this.ankor = new Point(x, y);
    }

    @Override
    public void drawOn(DrawSurface d) {
        triangle1 = new Polygon();
        triangle1.addPoint((int) ankor.getX() - 50, (int) ankor.getY() + 100); //left
        triangle1.addPoint((int) ankor.getX() + 50, (int) ankor.getY() + 100); //right
        triangle1.addPoint((int) ankor.getX(), (int) ankor.getY()); //top
        triangle2 = new Polygon();
        triangle2.addPoint((int) ankor.getX() - 50, (int) ankor.getY() + 130);
        triangle2.addPoint((int) ankor.getX() + 50, (int) ankor.getY() + 130);
        triangle2.addPoint((int) ankor.getX(), (int) ankor.getY() + 30);
        triangle3 = new Polygon();
        triangle3.addPoint((int) ankor.getX() - 50, (int) ankor.getY() + 160);
        triangle3.addPoint((int) ankor.getX() + 50, (int) ankor.getY() + 160);
        triangle3.addPoint((int) ankor.getX(), (int) ankor.getY() + 10);


        d.setColor(new Color(0, 102, 0));
        d.fillPolygon(triangle3);
        d.setColor(new Color(0, 153, 0));
        d.fillPolygon(triangle2);
        d.setColor(new Color(0, 204, 0));
        d.fillPolygon(triangle1);
        d.setColor(new Color(102, 51, 0));
        d.fillRectangle((int) ankor.getX() - 15, (int) ankor.getY() + 160, 30, 40);


    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {

    }
}
