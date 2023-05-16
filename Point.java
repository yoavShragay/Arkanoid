/**
 * @author Yoav Shragay <\yoavshra@gmail.com> 318730066s
 */
public class Point {
    private static final double EPSILON = Math.pow(10, -6);
    private double x;
    private double y;

    /**
     * constructor.
     *
     * @param x the x value of a point
     * @param y the y value of a point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance -- return the distance of this point to the other point.
     *
     * @param other variable Description another point
     * @return the distance between a point and another point
     */
    public double distance(Point other) {
        double x1 = this.x, y1 = this.y, x2 = other.x, y2 = other.y;
        return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
    }

    /**
     * equals -- return true is the points are equal, false otherwise.
     *
     * @param other variable Description another point
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return ((Math.abs(this.x - other.getX()) < EPSILON) && Math.abs(this.y - other.getY()) < EPSILON);
    }

    /**
     * @return the x value of the point.
     */
    public double getX() {
        return x;
    }

    /**
     * change the value of x.
     *
     * @param x variable Description the value I want to change
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y value of the point.
     */
    public double getY() {
        return y;
    }

    /**
     * change the value of y.
     *
     * @param y variable Description the value I want to change
     */
    public void setY(double y) {
        this.y = y;
    }
}
