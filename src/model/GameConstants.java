//package model;

import java.util.ArrayList;
import java.util.Arrays;

public class GameConstants {
    public static int PLAYER_INITIAL_MONEY = 2558;
    public static int JAIL_BAIL_VALUE = 50;

    // board related
    public static int BOARD_SIZE = 32;
    public static ArrayList<Integer>PROPERTIES_ID = 
        new ArrayList<>(Arrays.asList(2, 3, 4, 5, 7, 9, 10, 11, 14, 15, 17, 18, 20, 21, 22, 25, 27, 28, 29, 31));
    public static ArrayList<Integer>CARDS_ID = new ArrayList<>(Arrays.asList(6, 13, 23, 30));
    public static ArrayList<Integer>MONEY_ID = new ArrayList<>(Arrays.asList(1, 12, 19, 26));
    public static int PRISON_ID = 16;
}