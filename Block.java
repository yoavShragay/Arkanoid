import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Block.
 *
 * @author Yoav Shragay <\yoavshra@gmail.com> 318730066s
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private static final double EPSILON = Math.pow(10, -6);
    private final Rectangle blockRec;
    private final Point upperRightCorner;
    private final Point lowerRightCorner;
    private final Point lowerLeftCorner;
    /**
     * The Hit listeners.
     */
    private final List<HitListener> hitListeners = new ArrayList<>();
    private final Color color;


    /**
     * Instantiates a new Block.
     *
     * @param color          the color
     * @param blockRectangle the block rectangle
     */
    public Block(Color color, Rectangle blockRectangle) {
        this.color = color;
        this.blockRec = blockRectangle;
        //the corner of the block
        upperRightCorner = new Point(this.blockRec.getUpperLeft().getX() + this.blockRec.getWidth(),
                this.blockRec.getUpperLeft().getY());
        lowerRightCorner = new Point(this.blockRec.getUpperLeft().getX() + this.blockRec.getWidth(),
                this.blockRec.getUpperLeft().getY() + this.blockRec.getHeight());
        lowerLeftCorner = new Point(this.blockRec.getUpperLeft().getX(),
                this.blockRec.getUpperLeft().getY() + this.blockRec.getHeight());
    }

    /**
     * Return the "collision shape" of the object.
     *
     * @return the block shape
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return blockRec;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @param hitter          the ball that hit the block
     * @return the new velocity of the object after the hit
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        //case - the ball hit the corner of rhe block.
        if (collisionPoint.equals(this.blockRec.getUpperLeft()) || collisionPoint.equals(this.upperRightCorner)
                || collisionPoint.equals(this.lowerRightCorner) || collisionPoint.equals(this.lowerLeftCorner)) {
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        }
        //touching the left part of the block
        if ((collisionPoint.getX() == (this.blockRec.getUpperLeft().getX()))
                && Math.abs(collisionPoint.getX() - (this.blockRec.getUpperLeft().getX())) < EPSILON) {

            return new Velocity(-1 * Math.abs(currentVelocity.getDx()), currentVelocity.getDy());
            //touching the right part of the block
        } else if ((collisionPoint.getX() == (this.blockRec.getUpperLeft().getX() + this.blockRec.getWidth()))
                && (Math.abs(collisionPoint.getX()
                - (this.blockRec.getUpperLeft().getX() + this.blockRec.getWidth())) < EPSILON)) {

            return new Velocity(Math.abs(currentVelocity.getDx()), currentVelocity.getDy());
            //touching the upper part of the block
        } else if ((collisionPoint.getY() == this.blockRec.getUpperLeft().getY())
                && (Math.abs(collisionPoint.getY() - (this.blockRec.getUpperLeft().getY())) < EPSILON)) {

            return new Velocity(currentVelocity.getDx(), -1 * Math.abs(currentVelocity.getDy()));
            //touching the lower part of the block
        } else if ((collisionPoint.getY() == (this.blockRec.getUpperLeft().getY() + blockRec.getHeight()))
                && (Math.abs(collisionPoint.getY() - (this.blockRec.getUpperLeft().getY()
                + this.blockRec.getHeight())) < EPSILON)) {
            return new Velocity(currentVelocity.getDx(), Math.abs(currentVelocity.getDy()));
        }


        //if the ball didn't hit anything then the function will return the same velocity
        return currentVelocity;
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * draw the block on the surface.
     *
     * @param surface the surface that the ball is going to be written on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        Point upLeft = this.blockRec.getUpperLeft();
        surface.fillRectangle((int) upLeft.getX(), (int) upLeft.getY(), (int) blockRec.getWidth(),
                (int) blockRec.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) upLeft.getX(), (int) upLeft.getY(), (int) blockRec.getWidth(),
                (int) blockRec.getHeight());
    }

    /**
     * this function is irrelevant for now.
     */
    @Override
    public void timePassed() {


    }

    /**
     * adding the block to the game by adding it to the relevant arrays.
     *
     * @param g the game
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }


    /**
     * Remove from game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}

