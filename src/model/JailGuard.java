//package model
import assets.*;
import java.util.ArrayList;
import java.io.*;

public class JailGuard implements Serializable {
    private Integer totalJailTime;
    private ArrayList<Integer> playersJailTime;
    private Integer bail;

    public JailGuard(int numPlayers, int bail) {
        this.totalJailTime = SpaceConstants.TOTAL_JAIL_TIME;
        this.playersJailTime = new ArrayList<Integer>();
        for (int i = 0; i < numPlayers; i++) {
            playersJailTime.add(0);
        }
        this.bail = bail;
    }

    public void lockPlayer(Player player) {
        this.playersJailTime.set(player.getId(), this.totalJailTime);
        player.lock();
    }

    public void freePlayer(Player player) {
        this.playersJailTime.set(player.getId(), 0);
        player.free();
    }

    public int timeLocked(Player player) {
        return this.playersJailTime.get(player.getId());
    }

    // called at the end of turn
    public void reducePenalty(Player player) {
        int time = this.playersJailTime.get(player.getId());
        if (time > 0) {
            this.playersJailTime.set(player.getId(), time-1);
        }
    }

    public boolean canBail(Player player) {
        if (this.playersJailTime.get(player.getId()) != 0) {
            return false;
        }
        if (player.getMoney() < this.bail) {
            return false;
        }
        return true;
    }

    public void bailPlayer(Player player) {
        if (!this.canBail(player)) {
            throw new IllegalArgumentException("cannot bail player.");
        }
        this.freePlayer(player);
        player.pay(this.bail);
    }

    // returns if player is free
    public boolean freePlayerWithDice(Player player, int dice1, int dice2) {
        if (dice1 == dice2) {
            this.freePlayer(player);
            return true;
        }
        return false;
    }


    // GETTERS
    public int getTotalJailTime() {
        return this.totalJailTime;
    }

    public ArrayList<Integer> getPlayersJailTime() {
        return this.playersJailTime;
    }

    public int getBailValue() {
        return this.bail;
    }
}