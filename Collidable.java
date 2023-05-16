/**
 * @author Yoav Shragay <\yoavshra@gmail.com> 318730066s
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return the collision shape
     */
    Rectangle getCollisionRectangle();


    /**
     * returns to new velocity after the ball was hit.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @param hitter the ball that hit the object
     * @return the new velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
