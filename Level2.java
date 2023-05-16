import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level 2.
 */
public class Level2 implements LevelInformation {
    private static final double SPEED = 4;
    private static final int BORDER_WIDTH = 20;
    private static final double BLOCK_WIDTH = (50.67);
    private static final int BLOCK_HIGHT = 30;
    private final int gameWidth;
    private final int gamehight;


    /**
     * Instantiates a new Level 2.
     *
     * @param gameWidth the game width
     * @param gamehight the gamehight
     */
    public Level2(int gameWidth, int gamehight) {
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
        return (gameWidth / 4) * 3;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        ArrayList<FlickerStar> stars = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            FlickerStar star = new FlickerStar(Color.black);
            stars.add(star);

        }
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                // d.setColor(new Color(10,186,181));

                d.setColor(Color.black);
                d.fillRectangle(1, 1, gameWidth, gamehight);
                for (FlickerStar s : stars) {
                    s.drawOn(d);
                }
                d.setColor(new Color(255, 255, 153));
                d.fillCircle(110, 120, 40);
                d.setColor(Color.black);
                d.fillCircle(130, 110, 40);

            }

            @Override
            public void timePassed() {
                for (FlickerStar s : stars) {
                    s.timePassed();
                }
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
        ArrayList<Color> colorArr = createColorArr();
        for (int i = 0; i < numberOfBlocksToRemove(); i++) {
            Block newBlock = new Block(colorArr.get(i), (new Rectangle(new Point(BORDER_WIDTH + i * BLOCK_WIDTH,
                    gamehight / 3), BLOCK_WIDTH, BLOCK_HIGHT)));
            blockList.add(newBlock);
        }


        return blockList;
    }

    /**
     * Create color arr array list.
     *
     * @return the array list
     */
    public ArrayList<Color> createColorArr() {
        ArrayList<Color> colorArr = new ArrayList<>();
        colorArr.add(Color.red);
        colorArr.add(Color.red);
        colorArr.add(Color.orange);
        colorArr.add(Color.orange);
        colorArr.add(Color.yellow);
        colorArr.add(Color.yellow);
        colorArr.add(Color.green);
        colorArr.add(Color.green);
        colorArr.add(Color.green);
        colorArr.add(Color.blue);
        colorArr.add(Color.blue);
        colorArr.add(Color.pink);
        colorArr.add(Color.pink);
        colorArr.add(new Color(51, 204, 255));
        colorArr.add(new Color(51, 204, 255));
        return colorArr;
    }


    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
