//package model;

import java.util.ArrayList;
import java.util.Arrays;

public class GameConstants {
    public static int PLAYER_INITIAL_MONEY = 200;
    public static int JAIL_BAIL_VALUE = 200;

    // board related
    public static int BOARD_SIZE = 40;
    public static ArrayList<Integer>PROPERTIES_ID = new ArrayList<>(Arrays.asList(2, 3, 7));
    public static ArrayList<Integer>CARDS_ID = new ArrayList<>(Arrays.asList(6, 11, 24, 27, 32, 38));
    public static ArrayList<Integer>MONEY_ID = new ArrayList<>(Arrays.asList(16, 23));
    public static int PRISON_ID = 29;
}