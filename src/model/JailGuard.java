//package model
import assets.*;
import java.util.ArrayList;

public class JailGuard {
    private int totalJailTime;
    private ArrayList<Integer> playersJailTime;
    private int bail;

    public JailGuard(int numPlayers, int bail) {
        this.totalJailTime = SpaceConstants.TOTAL_JAIL_TIME;
        this.playersJailTime = new ArrayList<Integer>();
        for (int i = 0; i < numPlayers; i++) {
            playersJailTime.add(0);
        }
        this.bail = bail;
    }

    public void lockPlayer(int playerId, Player player) {
        this.playersJailTime.set(playerId, this.totalJailTime);
        player.lock();
    }

    public void freePlayer(int playerId, Player player) {
        this.playersJailTime.set(playerId, 0);
        player.free();
    }

    public int timeLocked(int playerId) {
        return this.playersJailTime.get(playerId);
    }

    // called at the end of turn
    public void reducePenalty(int playerId) {
        int time = this.playersJailTime.get(playerId);
        if (time > 0) {
            this.playersJailTime.set(playerId, time-1);
        }
    }

    public boolean canBail(int playerId, Player player) {
        if (this.playersJailTime.get(playerId) != 0) {
            return false;
        }
        if (player.getMoney() < this.bail) {
            return false;
        }
        return true;
    }

    public void bailPlayer(int playerId, Player player) {
        if (!this.canBail(playerId, player)) {
            throw new IllegalArgumentException("cannot bail player.");
        }
        this.freePlayer(playerId, player);
        player.pay(this.bail);
    }

    // returns if player is free
    public boolean freePlayerWithDice(int playerId, Player player, int dice1, int dice2) {
        if (dice1 == dice2) {
            this.freePlayer(playerId, player);
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