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

    // returns an default Info, to be populated
    public Info(Player player) {
        this.player = player;
        this.space = null;
        this.card = null;
        this.gotThroughStart = false;
        this.possible_actions = new ArrayList<>();
        this.gotLocked = false;
        this.numMoves = 0;
    }
}