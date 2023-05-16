import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;


/**
 * @author Yoav Shragay <\yoavshra@gmail.com> 318730066s
 */
public class Paddle implements Sprite, Collidable {
    private static final int MOOVING_SPEED = 10;
    private static final double HIGHT = 20;
    private static final Color COLOR = Color.ORANGE;
    private static final int BORDER_WIDTH = 20;
    private final biuoop.KeyboardSensor keyboard;
    private final GUI gui;
    private final ArrayList<Ball> balls;
    private final double width;
    private double ballSpeed = 4;

    private final Point paddleUpperLeft;
    private final Rectangle paddleRec;


    /**
     * Instantiates a new Paddle.
     *
     * @param keyboard the keyboard
     * @param gui      the gui
     * @param balls    the balls
     * @param width    the width
     */
    public Paddle(KeyboardSensor keyboard, GUI gui, ArrayList<Ball> balls, double width) {
        this.width = width;
        this.keyboard = keyboard;
        this.gui = gui;
        this.balls = balls;
        paddleUpperLeft = new Point((GameLevel.GAME_WIDTH / 2) - width / 2, GameLevel.GAME_HIGHT - HIGHT * 1.5);
        paddleRec = new Rectangle(paddleUpperLeft, width, HIGHT);
    }

    /**
     * Move the paddle.
     *
     * @param move the move
     */
    public void movePaddel(int move) {
        for (Ball b : balls) {
            if (between(b.getX(), paddleUpperLeft.getX(), paddleUpperLeft.getX() + width / 2)
                    && between(b.getY(), paddleUpperLeft.getY(), paddleUpperLeft.getY() + HIGHT)) {
                b.setCenterX(b.getX() - 2 * MOOVING_SPEED);
            } else if (between(b.getX(), paddleUpperLeft.getX() + width / 2, paddleUpperLeft.getX() + width)
                    && between(b.getY(), paddleUpperLeft.getY(), paddleUpperLeft.getY() + HIGHT)) {
                b.setCenterX(b.getX() + 2 * MOOVING_SPEED);
            }
        }
        paddleUpperLeft.setX(paddleUpperLeft.getX() + move);
        paddleRec.updateRectangle(paddleUpperLeft);

    }

    /**
     * Move left.
     */
    public void moveLeft() {
        if (paddleUpperLeft.getX() <= BORDER_WIDTH) {
            return;
        }
        movePaddel(-MOOVING_SPEED);


    }

    /**
     * Move right.
     */
    public void moveRight() {
        if (paddleUpperLeft.getX() + width >= gui.getDrawSurface().getWidth() - BORDER_WIDTH) {
            return;
        }
        movePaddel(MOOVING_SPEED);

    }

    /**
     * moving thh paddle according to what the user pressed.
     */
    @Override
    public void timePassed() {
        if (keyboard != null) {
            if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
                moveLeft();
            } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
                moveRight();
            }
        }
    }

    /**
     * draw the block on the surface.
     *
     * @param d the surface that the ball is going to be written on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(COLOR);
        d.fillRectangle((int) paddleUpperLeft.getX(), (int) paddleUpperLeft.getY(), (int) width, (int) HIGHT);
    }

    /**
     * check if a value is Between to others.
     *
     * @param xVal the x val
     * @param a    the a
     * @param b    the b
     * @return true or false depend on the outcome.
     */
    public boolean between(double xVal, double a, double b) {
        return xVal <= Math.max(a, b) && xVal >= Math.min(a, b);
    }

    /**
     * Return the "collision shape" of the object.
     *
     * @return the paddle shape
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return paddleRec;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @param hitter          the ball that hit the paddle
     * @return null
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity tempV = new Velocity(0, 0);
        //case - the ball hit the upper part of the paddle
        if (collisionPoint.getY() == this.paddleRec.getUpperLeft().getY()) {
            //block 1
            if (between(collisionPoint.getX(), paddleUpperLeft.getX(), paddleUpperLeft.getX() + (width / 5))) {
                return tempV.fromAngleAndSpeed(300, ballSpeed);
                //block 2
            } else if (between(collisionPoint.getX(), paddleUpperLeft.getX() + (width / 5),
                    paddleUpperLeft.getX() + 2 * (width / 5))) {
                return tempV.fromAngleAndSpeed(330, ballSpeed);
                //block 3
            } else if (between(collisionPoint.getX(), paddleUpperLeft.getX() + 2 * (width / 5),
                    paddleUpperLeft.getX() + 3 * (width / 5))) {
                return new Velocity(currentVelocity.getDx(), -Math.abs(currentVelocity.getDy()));
                //block 4
            } else if (between(collisionPoint.getX(), paddleUpperLeft.getX() + 3 * (width / 5),
                    paddleUpperLeft.getX() + 4 * (width / 5))) {
                return tempV.fromAngleAndSpeed(30, ballSpeed);
                //block 5
            } else if (between(collisionPoint.getX(), paddleUpperLeft.getX() + 4 * (width / 5),
                    paddleUpperLeft.getX() + width)) {
                return tempV.fromAngleAndSpeed(60, ballSpeed);
            }
            //case - the ball hit the left part of the paddle
        } else if ((collisionPoint.getX() == this.paddleRec.getUpperLeft().getX() && between(collisionPoint.getY(),
                paddleUpperLeft.getY(), paddleUpperLeft.getY() + HIGHT)) || currentVelocity.getDx() > 0) {
            return tempV.fromAngleAndSpeed(300, ballSpeed);
            //case - the ball hit the right part of paddle
        } else if ((collisionPoint.getX() == this.paddleRec.getUpperLeft().getX()
                + width && between(collisionPoint.getY(), paddleUpperLeft.getY(), paddleUpperLeft.getY() + HIGHT))
                || currentVelocity.getDx() < 0) {
            return tempV.fromAngleAndSpeed(60, ballSpeed);
        }
        return null;

    }

    /**
     * Sets ball speed.
     *
     * @param ballSpeed the ball speed
     */
    public void setBallSpeed(double ballSpeed) {
        this.ballSpeed = ballSpeed;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g the game
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
