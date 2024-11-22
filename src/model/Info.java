//package model

import java.util.ArrayList;
import assets.*;

public class Info {
    public Player player;
    public Space space;
    public Card card;
    public boolean gotThroughStart;
    public ArrayList<String>possible_actions;
    public boolean gotLocked;
    public int numMoves;

    // returns an empty Info, to be populated
    public Info() {

    }
}