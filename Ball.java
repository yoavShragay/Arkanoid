import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * @author Yoav Shragay <\yoavshra@gmail.com> 318730066s
 */
public class Ball implements Sprite {
    public static final double ANGLE = Math.toRadians(130);
    public static final double SPEED = 4;
    private final int r;
    private GameEnvironment game;
    private Point center;
    private int x;
    private int y;
    private java.awt.Color color;
    private Velocity ballVel;
    private Point lowerBorder = new Point(0, 0);
    private Point upperBorder = new Point(GameLevel.GAME_WIDTH, GameLevel.GAME_HIGHT);


    // constructors

    /**
     * a ball constructor.
     *
     * @param x     variable Description the x value of the center of the ball
     * @param y     variable Description the y value of the center of the ball
     * @param r     variable Description the radius of the ball
     * @param color the color of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.color = color;
        this.center = new Point(x, y);
        setVelocity(0, 0);
    }

    /**
     * a ball constructor.
     *
     * @param center variable Description the center of the ball
     * @param r      variable Description radius of the ball
     * @param color  variable Description the color of the ball (obviously)
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        setVelocity(0, 0);
    }

    /**
     * generate random colour and returns it.
     *
     * @return random colour
     */
    public static Color randColour() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color randomColor = new Color(r, g, b);
        return randomColor;
    }

    /**
     * Gets lower border.
     *
     * @return the lower borders of the ball
     */
    public Point getLowerBorder() {
        return this.lowerBorder;
    }

    /**
     * setter for the lower borders.
     *
     * @param lowerBorder variable Description lower borders of the ball
     */
    public void setLowerBorder(Point lowerBorder) {
        this.lowerBorder = lowerBorder;
    }

    /**
     * Gets upper border.
     *
     * @return the upper borders of the ball
     */
    public Point getUpperBorder() {
        return this.upperBorder;
    }

    /**
     * setter for the upper borders.
     *
     * @param upperBorder variable Description upper borders of the ball
     */
    public void setUpperBorder(Point upperBorder) {
        this.upperBorder = upperBorder;
    }

    /**
     * Gets x.
     *
     * @return the x value of the center of the ball.
     */
// accessors
    public int getX() {

        return (int) center.getX();
    }

    /**
     * Gets y.
     *
     * @return the y value of the center of the ball.
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Gets size.
     *
     * @return the radius of the ball.
     */
    public int getSize() {
        return r;
    }

    /**
     * Gets color.
     *
     * @return the colour of the ball.
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * setter for the ball colour.
     *
     * @param color variable Description the requested colour
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Sets game.
     *
     * @param game the game
     */
    public void setGame(GameEnvironment game) {
        this.game = game;
    }

    /**
     * Gets velocity.
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return ballVel;
    }

    /**
     * setter for the velocity of the ball.
     *
     * @param v variable Description requested ball velocity
     */
    public void setVelocity(Velocity v) {
        this.ballVel = v;
    }

    /**
     * setter for the velocity of the ball.
     *
     * @param dx variable Description the requested differential value of x
     * @param dy variable Description the requested differential value of y
     */
    public void setVelocity(double dx, double dy) {
        this.ballVel = new Velocity(dx, dy);
    }

    /**
     * return 1 if the value is positive and negative otherwise.
     *
     * @param x the x
     * @return the int
     */
    public int sign(double x) {
        if (x > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Sets center x.
     *
     * @param x the x value of the center
     */
    public void setCenterX(int x) {
        this.center.setX(x);
    }

    /**
     * Sets center y.
     *
     * @param y the y value of the center
     */
    public void setCenterY(int y) {
        this.y = y;
    }

    /**
     * Get trajectory line.
     *
     * @return the line
     */
    public Line getTrajectory() {
        Point start, end;
        start = this.center;
        end = new Point(this.center.getX() + getVelocity().getDx(), this.center.getY() + getVelocity().getDy());
        return new Line(start, end);

    }

    /**
     * moves the ball according to its current position.
     */
    public void moveOneStep() {
        Point collisionPoint, newCenter;
        Collidable collisionObject;
        Velocity smallStepVelocity;
        CollisionInfo collisionInfo;
        collisionInfo = game.getClosestCollision(getTrajectory());
        //case - the ball doesn't touch any collidable object
        if (collisionInfo == null) {
//            if (center.getX() >= upperBorder.getX() - (r + getVelocity().getDx()) && getVelocity().getDx() > 0) {
//                setVelocity(-getVelocity().getDx(), getVelocity().getDy());
//            }
//            if (center.getY() <= lowerBorder.getY() + r - getVelocity().getDy() && getVelocity().getDy() < 0) {
//                setVelocity(getVelocity().getDx(), -getVelocity().getDy());
//            }
//            if (center.getX() <= lowerBorder.getX() + r - getVelocity().getDx() && getVelocity().getDx() < 0) {
//                setVelocity(-getVelocity().getDx(), getVelocity().getDy());
//            }
            this.center = this.getVelocity().applyToPoint(this.center);
            //case - the ball touch a collidable object
        } else if (collisionInfo != null) {
            collisionPoint = collisionInfo.collisionPoint();
            collisionObject = collisionInfo.collisionObject();
            //getting the new velocity
            smallStepVelocity = collisionObject.hit(this, collisionPoint, getVelocity());
            setVelocity(smallStepVelocity);
            //move the ball a little back
            newCenter = new Point(collisionPoint.getX() - sign(getVelocity().getDx()),
                    collisionPoint.getY() - sign(getVelocity().getDy()));
            this.center = this.getVelocity().applyToPoint(newCenter);
        }

    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface variable Description the surface on which the ball is going to be printed
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), r);
    }

    /**
     * notify the ball time has passed.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * adding the ball into the game.
     *
     * @param g the game that the ball is going to be added to
     */
    @Override
    public void addToGame(GameLevel g) {
        setGame(g.getEnvironment());
        //setVelocity(SPEED * Math.sin(ANGLE), -1 * SPEED * Math.cos(ANGLE));
        g.getSprites().addSprite(this);
    }

    /**
     * Remove from game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
