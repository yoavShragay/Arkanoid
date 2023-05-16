import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Score indicator.
 *
 * @author Yoav Shragay <\yoavshra@gmail.com> 318730066s
 */
public class ScoreIndicator implements Sprite {
    private static final int WIDTH = 20;
    private Counter score;
    private Counter midScore;
    private String levelName;

    /**
     * Instantiates a new Score indicator.
     *
     * @param score     the score
     * @param levelName the level name
     */
    public ScoreIndicator(Counter score, String levelName) {
        this.score = score;
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.lightGray);
        d.fillRectangle(0, 0, GameLevel.GAME_WIDTH, WIDTH);
        d.setColor(Color.black);
        d.drawText((int) (GameLevel.GAME_WIDTH * 0.7), 18, "level name: " + levelName, 18);
        d.drawText(GameLevel.GAME_WIDTH / 2 - 20, 18, "score: " + score.getValue(), 20);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.getSprites().addSprite(this);
    }

    /**
     * Update score.
     *
     * @param score     the score
     * @param levelName the level name
     */
    public void updateScore(Counter score, String levelName) {
        int oldScore = this.score.getValue();
        this.score = score;
        this.score.increase(oldScore);

        this.levelName = levelName;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public Counter getScore() {
        return score;
    }
}
