import java.util.ArrayList;

/**
 * @author Yoav Shragay <\yoavshra@gmail.com> 318730066s
 */
public class GameEnvironment {
    // array of all the collidable objects in the game
    private final ArrayList<Collidable> collidables = new ArrayList<>();

    /**
     * Add collidable object to the collidables array.
     *
     * @param c the collidable object that going to be added
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }


    /**
     * Remove.
     *
     * @param c the c
     */
    public void remove(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Find the minimal colllision point from all the collidable object.
     *
     * @param trajectory the trajectory
     * @return the collidable
     */
    public Collidable findMinColllision(Line trajectory) {
        double endVal = 0, tmpVal;
        boolean flag = true;
        Collidable colllisionObject = null;
        Rectangle collisionRec;
        Point collisionPoint;
        for (Collidable c : collidables) {
            collisionRec = (c).getCollisionRectangle();
            collisionPoint = trajectory.closestIntersectionToStartOfLine(collisionRec);
            if (collisionPoint != null) {
                //in the first time the end value is going to be bigger
                if (flag) {
                    endVal = trajectory.start().distance(collisionPoint) + 1;
                    flag = false;
                }
                //tmp val is going to hold the current distance from the ball to the object
                tmpVal = trajectory.start().distance(collisionPoint);
                //here I save the lower value
                if (tmpVal < endVal) {
                    colllisionObject = c;
                }
                endVal = tmpVal;
            }
        }
        return colllisionObject;
    }

    /**
     * Gets the collidables array.
     *
     * @return the collidables
     */
    public ArrayList<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * Get the info of the  closest collision point .
     *
     * @param trajectory the trajectory
     * @return the collision info
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point collisionPoint;
        Collidable closestObject;
        Rectangle colisionRec;
        //finding the closest object
        closestObject = findMinColllision(trajectory);
        //finding the closest point in the object
        if (closestObject == null) {
            return null;
        }
        colisionRec = closestObject.getCollisionRectangle();
        collisionPoint = trajectory.closestIntersectionToStartOfLine(colisionRec);
        return new CollisionInfo(collisionPoint, closestObject);
    }
}
