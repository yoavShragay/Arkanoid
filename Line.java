/**
 * @author Yoav Shragay <\yoavshra@gmail.com> 318730066s
 */
public class Line {
    private static final double EPSILON = Math.pow(10, -6);
    private double x1;
    private double x2;
    private double y1;
    private double y2;
    private Point start = new Point(x1, y1);
    private Point end = new Point(x2, y2);

    // constructors

    /**
     * Line constructor.
     *
     * @param start variable Description: the start of the line
     * @param end   variable Description: the end of the line
     */
    public Line(Point start, Point end) {
        this.end = end;
        this.start = start;
        this.x1 = start.getX();
        this.y1 = start.getY();
        this.x2 = end.getX();
        this.y2 = end.getY();
    }

    /**
     * Line constructor.
     *
     * @param x1 variable Description: x of first point
     * @param y1 variable Description: y of first point
     * @param x2 variable Description: x of second point
     * @param y2 variable Description: y of second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.start.setX(x1);
        this.start.setY(y1);
        this.end.setX(x2);
        this.end.setY(y2);
    }

    /**
     * @return Return the length of the line
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * @return Returns the middle point of the line
     */
    public Point middle() {
        double midX = 0, midY = 0;
        midX = ((this.x1 + this.x2) / 2);
        midY = ((this.y1 + this.y2) / 2);
        return new Point(midX, midY);
    }

    /**
     * @return Returns the starting point of the line
     */
    public Point start() {
        return start;
    }

    /**
     * @return Returns the ending point of the line
     */
    public Point end() {
        return end;
    }

    /**
     * gets two values and returns the bigger one.
     *
     * @param a variable Description value
     * @param b variable Description other value
     * @return bigger value
     */
    private double max(double a, double b) {
        if (a <= b) {
            return b;
        }
        return a;
    }

    /**
     * gets two values and returns the smaller one.
     *
     * @param a variable Description value
     * @param b variable Description other value
     * @return bigger value
     */
    private double min(double a, double b) {
        if (a >= b) {
            return b;
        }
        return a;
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other variable Description other line than the one we have
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        //the same line
        if (equals(other)) {
            return true;
            //there is an intersection point
        } else if (intersectionWith(other) != null) {
            return true;
            //checking if one of the lines is contained in the other
        } else if (incline(x2, y2, x1, y1) == incline(other.x2, other.y2, other.x1, other.y1)) {
            return (between(this.x1, other.x2, other.x1) && between(this.y1, other.y2, other.y1))
                    || between(this.x2, other.x2, other.x1) && between(this.y2, other.y2, other.y1)
                    || between(other.x1, this.x1, this.x2) && between(other.y1, this.y1, this.y2);
        }
        return false;
    }

    /**
     * gets a value and check if the value is between the other values.
     *
     * @param xVal variable Description the value I want to check if between
     * @param a    the first value
     * @param b    the second value
     * @return true if the value is between the two other values and false otherwise
     */
    public boolean between(double xVal, double a, double b) {
        return (xVal <= max(a, b) && xVal >= min(a, b)) || Math.abs(xVal - max(a, b)) < EPSILON
                || Math.abs(xVal - min(a, b)) < EPSILON;
    }

    /**
     * get two points and return the incline of the line between them.
     *
     * @param x2 variable Description: x of second point
     * @param y2 variable Description: y of second point
     * @param x1 variable Description: x of first point
     * @param y1 variable Description: y of first point
     * @return the incline
     */

    private double incline(double x2, double y2, double x1, double y1) {
        if (y1 - y2 == 0 || x1 - x2 == 0) {
            return 0;
        } else {
            return ((y1 - y2) / (x1 - x2));
        }
    }

    /**
     * Returns the intersection point if the lines intersect,  and null otherwise.
     *
     * @param other the other line i want the check
     * @return the intersection point
     */
    public Point intersectionWith(Line other) {
        double incline1, incline2, newX, newY;
        //checking the inclines of both of the functions
        incline1 = incline(this.x2, this.y2, this.x1, this.y1);
        incline2 = incline(other.x2, other.y2, other.x1, other.y1);
        if (incline1 == incline2) {
            // case - both of the lines have the same incline,and they touch
            // exactly at the end of one line and the start of the other
            if (min(y1, this.y2) == max(other.y1, other.y2) && min(this.x1, this.x2) == max(other.x1, other.x2)) {
                return new Point(min(x1, x2), min(y1, y2));
            } else if (max(y1, y2) == min(other.y1, other.y2) && max(this.x1, this.x2) == min(other.x1, other.x2)) {
                return new Point(max(this.x1, this.x2), max(this.y1, this.y2));
            }
            // case - one of the lines is horizontal and the other is vertical
            if (x1 - x2 == 0 && other.y1 - other.y2 == 0 || other.x1 - other.x2 == 0 && y1 - y2 == 0) {
                if (min(this.y1, this.y2) == min(other.y1, other.y2)
                        && min(this.x1, this.x2) == min(other.x1, other.x2)) {
                    return new Point(min(this.x1, this.x2), min(this.y1, this.y2));
                }
                if (between(this.x1, other.x1, other.x2) && between(other.y1, this.y1, this.y2)) {
                    return new Point(this.x1, other.y1);
                }
                if (between(other.x1, this.x1, this.x2) && between(this.y1, other.y1, other.y2)) {
                    return new Point(other.x1, this.y1);
                }
            }
            //case - one of
            return null;
        }
        //case - one of the lines is vertical
        if (this.x1 - this.x2 == 0) {
            newY = incline2 * (this.x1 - other.x1) + other.y1;
            if (between(newY, this.y1, this.y2) && between(this.x1, other.x1, other.x2)) {
                return new Point(this.x1, newY);
            }
        } else if (other.x1 - other.x2 == 0) {
            newY = incline1 * (other.x1 - this.x1) + this.y1;
            if (between(newY, other.y1, other.y2) && between(other.x1, this.x1, this.x2)) {
                return new Point(other.x1, newY);
            }

        }
        //this line is for calculating the x value of the intersection point
        newX = (((-1 * incline2 * other.x1) + (incline1 * x1) + (other.y1 - y1)) / (incline1 - incline2));
        if (between(newX, x1, x2) && between(newX, other.x1, other.x2)) {
            //this line is for checking the y value of the intersection point
            newY = incline1 * (newX - x1) + y1;
            return new Point(newX, newY);
        } else {
            return null;
        }

    }

    /**
     * return true is the lines are equal, false otherwise.
     *
     * @param other the other line i want the check
     * @return true if the lines intersect and false otherwise.
     */
    public boolean equals(Line other) {
        return start.getX() == other.start.getX() && start.getY() == other.start.getY() && end.getX()
                == other.end.getX() && end.getY() == end.getY();
    }

    /**
     * return the closest intersection point to the start of the line.
     *
     * @param rect the rectangle that the ball collided with
     * @return the closest point of intersection
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        if (rect == null) {
            return null;
        }

        Point[] closestPointsArray = new Point[4];
        int count = 0;
        Point firstP, seconedP;
        //getting all the intersection points into the list
        rect.intersectionPoints(new Line(this.start, this.end));

        for (int i = 0; i < rect.getIntersectionPoints().size(); i++) {
            if (rect.getIntersectionPoints().get(i) != null) {
                closestPointsArray[count] = (Point) rect.getIntersectionPoints().get(i);
                count++;
            }
        }
        if (count == 0) {
            return null;
        } else if (count == 1) {
            return closestPointsArray[count - 1];
        } else if (count == 2) {
            firstP = closestPointsArray[0];
            seconedP = closestPointsArray[1];
            if (firstP.distance(this.start) > seconedP.distance(this.start)) {
                return seconedP;
            } else {
                return firstP;
            }
        }
        return null;
    }

}
