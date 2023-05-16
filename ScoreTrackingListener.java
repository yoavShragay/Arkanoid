/**
 * @author Yoav Shragay <\yoavshra@gmail.com> 318730066s
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;
    private final GameLevel game;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param game         the game
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(GameLevel game, Counter scoreCounter) {
        this.currentScore = scoreCounter;
        this.game = game;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }

    /**
     * End the game.
     */
    public void endGame() {
        currentScore.increase(100);
    }
}
