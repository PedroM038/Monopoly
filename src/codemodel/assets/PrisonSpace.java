/*
 * The PrisonSpace class represents the jail from the game
 * It only stores data about the jail, and does not affect the players
 * 
 */

package codemodel.assets;

public class PrisonSpace extends Space {
    public PrisonSpace() {
        this.description = "PRISON";
    }

    public int getTotalJailTime() {
        return SpaceConstants.TOTAL_JAIL_TIME;
    }
}