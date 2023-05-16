/**
 * @author Yoav Shragay <\yoavshra@gmail.com> 318730066s
 */
public class PrintingHitListener implements HitListener {
    /**
     * print that a hit happened.
     *
     * @param beingHit the object that being hit
     * @param hitter the Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}
