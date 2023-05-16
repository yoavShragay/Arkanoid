import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;


/**
 * The type Game flow.
 */
public class GameFlow {
    /**
     * The constant GAME_WIDTH.
     */
    public static final int GAME_WIDTH = 800;
    /**
     * The constant GAME_HIGHT.
     */
    public static final int GAME_HIGHT = 600;
    /**
     * The Score.
     */
    private ScoreIndicator score = new ScoreIndicator(new Counter(0), "k");
    private final AnimationRunner animationRunner;
    private final GUI gui;
    private final KeyboardSensor ks;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar  the ar
     * @param ks  the ks
     * @param gui the gui
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.gui = gui;
        this.animationRunner = ar;
        this.ks = ks;
    }

    /**
     * Lose.
     */
    public void lose() {
//
        animationRunner.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY,
                new YouLooseAnimation(this.ks, score.getScore().getValue())));
        gui.close();


    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {

        for (LevelInformation levelInfo : levels) {

            GameEnvironment environment = new GameEnvironment();
            SpriteCollection sprites = new SpriteCollection();

            GameLevel level = new GameLevel(gui, environment, sprites, animationRunner, levelInfo, score);
            level.initialize();

            while (level.getBlockCounter().getValue() != 0 && level.getBallCounter().getValue() != 0) {
                level.run();
            }
            if (level.getBallCounter().getValue() == 0) {
                lose();
                break;
            }
        }
        animationRunner.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY,
                new YouWinAnimation(this.ks, score.getScore().getValue())));
        gui.close();

    }
}
