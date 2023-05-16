import java.util.ArrayList;
import java.util.List;


/**
 * The type Rectangle.
 *
 * @author Yoav Shragay <\yoavshra@gmail.com> 318730066s
 */
public class Rectangle {
    private final double width;
    private final double height;
    private Point upperLeft;
    private final java.util.List intersectionPoints = new ArrayList<>();
    private Line upperLine;
    private Line lowerLine;
    private Line leftLine;
    private Line rightLine;
    private Line[] recLineArr = new Line[4];


    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft the upper left point
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        updateRectangle(upperLeft);
    }

    /**
     * Update rectangle.
     *
     * @param newUpperLeft the new upper left point
     */
    public void updateRectangle(Point newUpperLeft) {
        this.upperLeft = newUpperLeft;
        this.upperLine = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + width, upperLeft.getY());
        this.lowerLine = new Line(upperLeft.getX(), upperLeft.getY() + height, upperLeft.getX() + width,
                upperLeft.getY() + height);
        this.leftLine = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY() + height);
        this.rightLine = new Line(upperLeft.getX() + width, upperLeft.getY(), upperLeft.getX() + width,
                getUpperLeft().getY() + height);
        recLineArr = new Line[]{upperLine, lowerLine, leftLine, rightLine};
    }

    /**
     * Gets intersection points.
     *
     * @return the intersection points
     */
    public List getIntersectionPoints() {
        return intersectionPoints;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line the line thr the rec is intersecting with
     * @return List of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        intersectionPoints.clear();
        Point intersectPoint;
        int count = 0;
        for (Line l : recLineArr) {

            intersectPoint = l.intersectionWith(line);
            if (intersectPoint != null) {
                //System.out.println(intersectPoint.getY());

                intersectionPoints.add(intersectPoint);
                count++;
            }
        }
        return intersectionPoints;
    }


    /**
     * Return the width of the rectangle.
     *
     * @return the double
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the height of the rectangle.
     *
     * @return the double
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Return the upper left point of the rectangle.
     *
     * @return the point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}
