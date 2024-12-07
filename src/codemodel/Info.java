/*
 * The Info class stores data about a play in the game
 * It stores all data necessary to give the controller to show in vision
 */

package codemodel;

import codemodel.assets.*;
import java.util.ArrayList;

public class Info {
    public Player player;
    public String name;
    public Space space;
    public Integer spaceId;
    public Card card;
    public boolean gotThroughStart;
    public ArrayList<String>possible_actions;
    public boolean gotLocked;
    public Integer numMoves;

    // returns an default Info, to be populated
    public Info(Player player) {
        this.player = player;
        this.name = player.getName();
        this.space = null;
        this.spaceId = 0;
        this.card = null;
        this.gotThroughStart = false;
        this.possible_actions = new ArrayList<>();
        this.gotLocked = false;
        this.numMoves = 0;
    }
}