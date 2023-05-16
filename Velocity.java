/**
 * @author Yoav Shragay <\yoavshra@gmail.com> 318730066s
 */
public class Velocity {

    private final double dx;
    private final double dy;

    private double speed;

    /**
     * Velocity constructor.
     *
     * @param dx variable Description the differential value of x
     * @param dy variable Description the differential value of y
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * gets an angle and speed and return the dx and dy values that will represent those values.
     *
     * @param angle variable Description the angle of the ball movement
     * @param speed variable Description the speed of the ball movement
     * @return a velocity with the desired speed and angle
     */
    public Velocity fromAngleAndSpeed(double angle, double speed) {
        double dy;
        double dx;
        // setSpeed(speed);
        angle = angle % 360;
        angle = Math.toRadians(angle);
        dy = -1 * speed * (Math.cos(angle));
        dx = speed * (Math.sin(angle));
        return new Velocity(dx, dy);
    }

    /**
     * @return the dx value of the velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return the dy value of the velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y) and return a new point, with position (x+dx, y+dy).
     *
     * @param p variable Description the point i want to add velocity to
     * @return the velocity after the addition
     */
    public Point applyToPoint(Point p) {
        //Point velPoint = new Point()
        Point velPoint = new Point((p.getX() + this.dx), (p.getY() + this.dy));
        return velPoint;
    }
}