import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Sprite collection.
 *
 * @author Yoav Shragay <\yoavshra@gmail.com> 318730066s
 */
public class SpriteCollection {

    private final ArrayList<Sprite> sprites = new ArrayList<>();

    /**
     * Add sprite.
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Remove.
     *
     * @param s the s
     */
    public void remove(Sprite s) {
        sprites.remove(s);
    }

    /**
     * Notify all the sprites time has passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<>(sprites);
        for (Sprite s : spritesCopy) {
            s.timePassed();
        }

    }

    /**
     * calls all the sprites and drawing the on the drawSurface.
     *
     * @param d the DrawSurface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }

    /**
     * Print all.
     */
    public void printAll() {
        for (Sprite s : sprites) {
            System.out.println(s);
        }
    }
}
