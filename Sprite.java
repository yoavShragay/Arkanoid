import biuoop.DrawSurface;
/**
 * @author Yoav Shragay <\yoavshra@gmail.com> 318730066s
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d the surface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * adding the sprite to the game.
     *
     * @param g the game
     */
    void addToGame(GameLevel g);
}
