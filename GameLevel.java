import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;

/**
 * The type Game.
 *
 * @author Yoav Shragay <\yoavshra@gmail.com> 318730066s
 */
public class GameLevel implements Animation {
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HIGHT = 600;
    private static final int SCORE_LINE_HIGHT = 20;
    private static final int BORDER_WIDTH = 20;
    private static final int NUM_OF_BALLS = 100;
    private static final int BALL_RADIUS = 5;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HIGHT = 30;
    private static final Color COLOR = Color.gray;
    private static final int NUM_OF_ROWS = 6;
    private static final int RED = 2;
    private static final int GREEN = 30;
    private static final int BLUE = 2;
    //this is the number of blocks in the longest line
    private static final int NUM_OF_BLOCKS = 13;
    //this is the space from which the blocks are gonna start from
    private static final int SPACE = 100;
    private final ArrayList<Ball> balls = new ArrayList<>();

    private final Counter blockCounter = new Counter(0);
    private final BlockRemover remover = new BlockRemover(this, blockCounter);

    private final Counter ballCounter = new Counter(0);
    private final BallRemover ballRemove = new BallRemover(this, ballCounter);

    private final Counter score = new Counter(0);
    private final ScoreTrackingListener scoreListener = new ScoreTrackingListener(this, score);
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner runner;
    private final LevelInformation levelInformation;
    private final ScoreIndicator scoreIndicator;
    private boolean running;
    private Block death;


    /**
     * Instantiates a new Game level.
     *
     * @param gui              the gui
     * @param environment      the environment
     * @param sprites          the sprites
     * @param runner           the runner
     * @param levelInformation the level information
     * @param scoreIndicator   the score indicator
     */
    public GameLevel(GUI gui, GameEnvironment environment, SpriteCollection sprites, AnimationRunner runner,
                     LevelInformation levelInformation, ScoreIndicator scoreIndicator) {
        this.gui = gui;
        this.environment = environment;
        this.sprites = sprites;
        this.runner = runner;
        this.keyboardSensor = gui.getKeyboardSensor();
        this.levelInformation = levelInformation;
        scoreIndicator.updateScore(score, levelInformation.levelName());
        this.scoreIndicator = scoreIndicator;

    }

    /**
     * Gets block counter.
     *
     * @return the block counter
     */
    public Counter getBlockCounter() {
        return blockCounter;
    }

    /**
     * Gets the environment.
     *
     * @return the environment
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * Sets environment.
     *
     * @param environment the environment
     */
    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }

    /**
     * Gets ball counter.
     *
     * @return the ball counter
     */
    public Counter getBallCounter() {
        return ballCounter;
    }

    /**
     * Sets keyboard sensor.
     *
     * @param keyboardSensor the keyboard sensor
     */
    public void setKeyboardSensor(KeyboardSensor keyboardSensor) {
        this.keyboardSensor = keyboardSensor;
    }

    /**
     * Gets sprites array.
     *
     * @return the sprites
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * Sets sprites.
     *
     * @param sprites the sprites
     */
    public void setSprites(SpriteCollection sprites) {
        this.sprites = sprites;
    }

    /**
     * Sets runner.
     *
     * @param runner the runner
     */
    public void setRunner(AnimationRunner runner) {
        this.runner = runner;
    }

    /**
     * Get the collidable array list.
     *
     * @return the array list
     */
    public ArrayList getCollidable() {
        return environment.getCollidables();
    }

    /**
     * Add collidable to the environment.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Create blocks.
     */
    public void createBlocks() {
        for (Block b : levelInformation.blocks()) {
            blockCounter.increase(1);
            b.addHitListener(remover);
            b.addHitListener(scoreListener);
            b.addToGame(this);
        }
//        for (int j = 0; j < NUM_OF_ROWS; j++) {
//            Color color = new Color((j * RED) + RED, (j * GREEN) + GREEN, (j * RED) + RED);
//            for (int i = 0; i < NUM_OF_BLOCKS - (j * 2); i++) {
//
//                Block newBlock = new Block(color, (new Rectangle(new Point(GAME_WIDTH - (BLOCK_WIDTH + BORDER_WIDTH)
//                        - (i * BLOCK_WIDTH), (BLOCK_HIGHT * j) + SPACE), BLOCK_WIDTH, BLOCK_HIGHT)));
//                newBlock.addToGame(this);
//                blockCounter.increase(1);
//                newBlock.addHitListener(remover);
//                newBlock.addHitListener(scoreListener);
//            }
//        }
    }

    /**
     * Sets gui.
     *
     * @param gui the gui
     */
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    /**
     * create the blocks that going to be used as borders.
     */


    /**
     * Create the paddle.
     *
     * @param gui   the gui that going to be used
     * @param balls the balls that can touch the paddle
     * @param width the width
     */
    public void createPaddle(GUI gui, ArrayList<Ball> balls, int width) {
        Paddle paddle = new Paddle(keyboardSensor, gui, balls, width);
        paddle.setBallSpeed(levelInformation.getSpeed());
        paddle.addToGame(this);
    }

    /**
     * Create balls.
     *
     * @param x the amount of balls that going to be made
     */
    public void createBalls(int x) {

        for (int i = 0; i < x; i++) {
            Ball ball = new Ball(new Point((GAME_WIDTH / 2), GAME_HIGHT - 60), BALL_RADIUS, COLOR);
            ball.setVelocity(levelInformation.initialBallVelocities().get(i));
            balls.add(ball);
            ballCounter.increase(1);
            ball.addToGame(this);
        }
    }

    /**
     * Create death block.
     *
     * @param ballRemove the ball remove
     */
    public void createDeathBlock(BallRemover ballRemove) {
        death = new Block(Color.BLACK, new Rectangle(new Point(-BORDER_WIDTH, 600), GAME_WIDTH
                + 2 * BORDER_WIDTH, BLOCK_HIGHT));
        death.addToGame(this);
        death.addHitListener(ballRemove);
    }

    /**
     * Score line.
     *
     * @param d the d
     */
    public void scoreLine(DrawSurface d) {
        d.setColor(Color.lightGray);
        d.fillRectangle(0, 0, GAME_WIDTH, SCORE_LINE_HIGHT);
    }

    /**
     * Sets background.
     */
    public void settingBackground() {
        levelInformation.getBackground().addToGame(this);

    }

    /**
     * Initialize a new game.
     */
    public void initialize() {

        //creating
        settingBackground();
        createDeathBlock(ballRemove);
        createBlocks();
        createBalls(levelInformation.numberOfBalls());
        createPaddle(gui, balls, levelInformation.paddleWidth());
        scoreIndicator.addToGame(this);
    }

    /**
     * Run.
     */
    public void run() {
        for (int i = 0; i < 4; i++) {
            this.runner.run(new CountdownAnimation(2, 3 - i, this.sprites));
        }
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run2() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.cyan);
            d.fillRectangle(1, 1, GAME_WIDTH, GAME_HIGHT);
            scoreLine(d);
            this.sprites.drawAllOn(d);
            if (blockCounter.getValue() == 0 || ballCounter.getValue() == 0) {
                if (blockCounter.getValue() == 0) {
                    scoreListener.endGame();

                }
                sprites.drawAllOn(d);
                gui.show(d);
                sleeper.sleepFor(500);
                gui.close();
                return;
            }

            this.sprites.notifyAllTimePassed();
            gui.show(d);

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }

    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        environment.remove(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {

        sprites.remove(s);
    }

    /**
     * Clear balls.
     */
    public void clearBalls() {
        for (Ball b : balls) {
            b.removeFromGame(this);
        }
        death.removeHitListener(ballRemove);

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        scoreLine(d);
        if (this.keyboardSensor.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(this.keyboardSensor)));
        }

        this.sprites.drawAllOn(d);

        if (blockCounter.getValue() == 0 || ballCounter.getValue() == 0) {
            if (blockCounter.getValue() == 0) {
                scoreListener.endGame();
            }
            sprites.drawAllOn(d);
            this.running = false;
        }

        this.sprites.notifyAllTimePassed();
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
