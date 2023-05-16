import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Testlevel.
 */
public class Testlevel implements LevelInformation {

    private static final double SPEED = 4;
    private static final int BORDER_WIDTH = 20;
    private static final double BLOCK_WIDTH = (50.67);
    private static final int BLOCK_HIGHT = 30;
    private final int gameWidth;
    private final int gamehight;


    /**
     * Instantiates a new Testlevel.
     *
     * @param gameWidth the game width
     * @param gamehight the gamehight
     */
    public Testlevel(int gameWidth, int gamehight) {
        this.gameWidth = gameWidth;
        this.gamehight = gamehight;
    }

    @Override
    public double getSpeed() {
        return SPEED;
    }

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity tempV = new Velocity(0, 0);
        List<Velocity> velocityList = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            velocityList.add(tempV.fromAngleAndSpeed(-67 + (i * 15), SPEED));
        }
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
        return "levelx";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.ORANGE);
                d.fillRectangle(1, 1, gameWidth, gamehight);

            }

            @Override
            public void timePassed() {

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
        for (int i = 0; i < numberOfBlocksToRemove(); i++) {
            Block newBlock = new Block(Color.blue,
                    (new Rectangle(new Point(BORDER_WIDTH + i * BLOCK_WIDTH, gamehight / 3), BLOCK_WIDTH, BLOCK_HIGHT)));
            blockList.add(newBlock);
        }


        return blockList;
    }


    @Override
    public int numberOfBlocksToRemove() {
        return 10;
    }
}


