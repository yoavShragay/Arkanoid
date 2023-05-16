import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level 4.
 */
public class Level4 implements LevelInformation {
    private static final double SPEED = 4;
    private static final int BORDER_WIDTH = 20;
    private static final double BLOCK_WIDTH = (50.67);
    private static final int BLOCK_HIGHT = 30;
    private static final int RED = 2;
    private static final int GREEN = 30;
    private static final int BLUE = 2;
    private final int gameWidth;
    private final int gamehight;

    /**
     * Instantiates a new Level 4.
     *
     * @param gameWidth the game width
     * @param gamehight the gamehight
     */
    public Level4(int gameWidth, int gamehight) {
        this.gameWidth = gameWidth;
        this.gamehight = gamehight;
    }

    @Override
    public double getSpeed() {
        return SPEED;
    }

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity tempV = new Velocity(0, 0);
        List<Velocity> velocityList = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            velocityList.add(tempV.fromAngleAndSpeed(-30 + (i * 30), SPEED));
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
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(51, 193, 255));
                d.fillRectangle(1, 1, gameWidth, gamehight);
                Tree tree = new Tree(250, 400);
                tree.drawOn(d);

                d.setColor(new Color(0, 150, 0));
                d.fillOval(gameWidth / 2 - 150, (int) (gamehight * 0.75 + 60), 700, 700);
                d.setColor(new Color(0, 255, 80));
                d.fillOval(-200, (int) (gamehight * 0.75 + 50), 700, 700);

                Tree t2 = new Tree(150, 320);
                t2.drawOn(d);
                Tree t3 = new Tree(650, 320);
                t3.drawOn(d);
                Tree t4 = new Tree(460, 360);
                t4.drawOn(d);


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
        ArrayList<Color> colors = new ArrayList<>();
        colors.add(Color.gray);
        colors.add(Color.red);
        colors.add(Color.yellow);
        colors.add(Color.green);
        colors.add(Color.white);
        colors.add(Color.pink);
        colors.add(Color.cyan);
        List<Block> blockList = new ArrayList<>();
        for (int j = 0; j < 7; j++) {
            //Color color = new Color((j * RED) + RED, (j * GREEN) + GREEN, (j * RED) + RED);
            for (int i = 0; i < 15; i++) {
                Block newBlock = new Block(colors.get(j),
                        (new Rectangle(new Point(BORDER_WIDTH + i * BLOCK_WIDTH, 100 + j * BLOCK_HIGHT),
                                BLOCK_WIDTH, BLOCK_HIGHT)));
                blockList.add(newBlock);
            }
        }

        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15 * 7;
    }
}
