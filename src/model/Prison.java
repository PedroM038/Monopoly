package model;

import java.util.ArrayList;

public class Prison extends Space {
    private ArrayList<int> playersId;

    class Prison() {
        this.playersId = new ArrayList<int>();
    }

    public void lockPlayer(int playerId) {
        if !this.containsPlayer(playersId) {
            this.playersId.add(playerId);
        }
    }

    public int freePlayer(int playerId) {
        for (int i = 0; i < this.playersId.size(); i++) {
            if (this.playersId.get(i) == playerId) {
                this.playersId.remove(i);
                return i;
            }
        }
        throw new NotFoundException("Player not on prison");
    }

    public boolean containsPlayer(int playerId) {
        for (int i = 0; i < this.playersId.size(); i++) {
            if (this.playersId.get(i) == playerId) {
                return true;
            }
        }
        return false;
    }
}