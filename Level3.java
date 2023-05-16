import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Level 3.
 */
public class Level3 implements LevelInformation {
    private static final double SPEED = 4;
    private static final int BORDER_WIDTH = 20;
    private static final double BLOCK_WIDTH = (50.67);
    private static final int BLOCK_HIGHT = 30;
    private static final int RED = 20;
    private static final int GREEN = 2;
    private static final int BLUE = 40;
    private final int gameWidth;
    private final int gamehight;

    /**
     * Instantiates a new Level 3.
     *
     * @param gameWidth the game width
     * @param gamehight the gamehight
     */
    public Level3(int gameWidth, int gamehight) {
        this.gameWidth = gameWidth;
        this.gamehight = gamehight;
    }

    @Override
    public double getSpeed() {
        return SPEED;
    }

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity tempV = new Velocity(0, 0);
        List<Velocity> velocityList = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            velocityList.add(tempV.fromAngleAndSpeed(-33 + (33 * 2 * i), SPEED));
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
        return "Green3";
    }

    @Override
    public Sprite getBackground() {
//        ArrayList<Color> colors=new ArrayList<>();
//        for(int i=0;i<3;i++){
//
//       }
        SpaceShip firstS = new SpaceShip(200, 450, Color.green, 80, 4);
        SpaceShip seconedS = new SpaceShip(366, 350, Color.blue, 100, 3);
        SpaceShip thirdS = new SpaceShip(410, 210, Color.red, 150, 2);
        ArrayList<ShootingStar> stars = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            ShootingStar star = new ShootingStar(Color.black);
            stars.add(star);

        }

        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.black);
                d.fillRectangle(1, 1, gameWidth, gamehight);
                for (ShootingStar s : stars) {
                    s.drawOn(d);
                }

                firstS.drawOn(d);
                seconedS.drawOn(d);
                thirdS.drawOn(d);

            }

            @Override
            public void timePassed() {
                firstS.timePassed();
                seconedS.timePassed();
                thirdS.timePassed();
                for (ShootingStar s : stars) {
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
        ArrayList<Color> colors = new ArrayList<>();
        colors.add(Color.gray);
        colors.add(Color.red);
        colors.add(Color.yellow);
        colors.add(Color.blue);
        colors.add(Color.white);
        List<Block> blockList = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
            //Color color = new Color((j * RED) + RED, (j * GREEN) + GREEN, (j * BLUE) + BLUE);
            for (int i = 0; i < numberOfBlocksToRemove() - (j * 2); i++) {

                Block newBlock = new Block(colors.get(j),
                        (new Rectangle(new Point(gameWidth - (BLOCK_WIDTH + BORDER_WIDTH)
                        - (i * BLOCK_WIDTH), (BLOCK_HIGHT * j) + 100), BLOCK_WIDTH, BLOCK_HIGHT)));
                blockList.add(newBlock);
            }
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 13;
    }
}
