import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Level 1.
 */
public class Level1 implements LevelInformation {
    private static final double SPEED = 4;
    private static final int BORDER_WIDTH = 20;
    private static final double BLOCK_WIDTH = 30;
    private static final int BLOCK_HIGHT = 30;
    private final int gameWidth;
    private final int gamehight;
    private int ballSize;
    private final int counter;


    /**
     * Instantiates a new Level 1.
     *
     * @param gameWidth the game width
     * @param gamehight the gamehight
     */
    public Level1(int gameWidth, int gamehight) {
        this.gameWidth = gameWidth;
        this.gamehight = gamehight;
        ballSize = 100;
        counter = 0;
    }

    @Override
    public double getSpeed() {
        return SPEED;
    }

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity tempV = new Velocity(0, 0);
        List<Velocity> velocityList = new ArrayList<>();
        velocityList.add(tempV.fromAngleAndSpeed(0, SPEED));
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {

            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.green);
                d.fillRectangle(1, 1, gameWidth, gamehight);
                d.setColor(Color.white);
                d.fillCircle((int) (gameWidth / 2 - BORDER_WIDTH - 6 + BLOCK_WIDTH / 2),
                        gamehight / 3 + BLOCK_HIGHT / 2, ballSize);
                d.setColor(Color.black);
                d.fillCircle((int) (gameWidth / 2 - BORDER_WIDTH - 6 + BLOCK_WIDTH / 2),
                        gamehight / 3 + BLOCK_HIGHT / 2, ballSize - 10);
                d.setColor(Color.cyan);
                d.fillCircle((int) (gameWidth / 2 - BORDER_WIDTH - 6 + BLOCK_WIDTH / 2),
                        gamehight / 3 + BLOCK_HIGHT / 2, ballSize - 30);
                d.setColor(Color.red);
                d.fillCircle((int) (gameWidth / 2 - BORDER_WIDTH - 6 + BLOCK_WIDTH / 2),
                        gamehight / 3 + BLOCK_HIGHT / 2, ballSize - 50);

            }

            @Override
            public void timePassed() {
                ballSize = ballSize - 1;
            }

            @Override
            public void addToGame(GameLevel g) {
                g.addSprite(this);
                createBorders(g);
            }
        };
    }

    private void createBorders(GameLevel g) {
        Block up = new Block(Color.darkGray, new Rectangle(new Point(0, BORDER_WIDTH), gameWidth, BORDER_WIDTH));
        up.addToGame(g);
        Block left = new Block(Color.darkGray, new Rectangle(new Point(0, BORDER_WIDTH), BORDER_WIDTH, gamehight));
        left.addToGame(g);
        Block right = new Block(Color.darkGray, new Rectangle(new Point(gameWidth - BORDER_WIDTH, BORDER_WIDTH),
                BORDER_WIDTH, gamehight));
        right.addToGame(g);

    }


    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        Block newBlock = new Block(Color.red, (new Rectangle(new Point(gameWidth / 2 - BORDER_WIDTH - 6,
                gamehight / 3), BLOCK_WIDTH, BLOCK_HIGHT)));
        blockList.add(newBlock);
        return blockList;
    }


    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
