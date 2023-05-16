import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Yoav Shragay <\yoavshra@gmail.com> 318730066s
 */
public class Ass6Game {
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HIGHT = 600;

    /**
     * New game.
     *
     * @param levelsList the levels list
     */
    public static void newGame(ArrayList<Integer> levelsList) {
        //setting the game
        GUI gui = new GUI("arkanoid", GAME_WIDTH, GAME_HIGHT);
        AnimationRunner runner = new AnimationRunner(gui, 60);

        //setting the levels
        List<LevelInformation> levels = new ArrayList<>();
        Level1 level1 = new Level1(GAME_WIDTH, GAME_HIGHT);
        Level2 level2 = new Level2(GAME_WIDTH, GAME_HIGHT);
        Level3 level3 = new Level3(GAME_WIDTH, GAME_HIGHT);
        Level4 level4 = new Level4(GAME_WIDTH, GAME_HIGHT);

        //adding the levels
        for (Integer i : levelsList) {
            if (i == 1) {
                levels.add(level1);
            } else if (i == 2) {
                levels.add(level2);
            } else if (i == 3) {
                levels.add(level3);
            } else if (i == 4) {
                levels.add(level4);
            }
        }

        GameFlow gameFlow = new GameFlow(runner, gui.getKeyboardSensor(), gui);
        gameFlow.runLevels(levels);
    }

    private static boolean isNumber(String val) {
        for (int i = 0; i < 10; i++) {
            if (Objects.equals(val, String.valueOf(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Create list array list.
     *
     * @param userList the user list
     * @return the array list
     */
    public static ArrayList<Integer> createList(String[] userList) {
        ArrayList<Integer> levelsList = new ArrayList<>();
        for (int i = 0; i < userList.length; i++) {
            if (isNumber(userList[i])) {
                if (0 < Integer.parseInt(userList[i]) && Integer.parseInt(userList[i]) < 5) {
                    levelsList.add(Integer.parseInt(userList[i]));
                }
            }
        }
        if (levelsList.isEmpty()) {
            for (int i = 1; i < 5; i++) {
                levelsList.add(i);
            }
        }
        return levelsList;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @author Yoav Shragay <\yoavshra@gmail.com> 318730066s
     */
    public static void main(String[] args) {
        newGame(createList(args));
    }
}
