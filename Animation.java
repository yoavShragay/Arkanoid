import biuoop.DrawSurface;

/**
 * @author Yoav Shragay <\yoavshra@gmail.com> 318730066s
 */
public interface Animation {
    /**
     * Do one frame.
     *
     * @param d the DrawSurface
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should stop boolean.
     *
     * @return the stop value
     */
    boolean shouldStop();
}
