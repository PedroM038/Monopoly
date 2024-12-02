/*
 * The Vacation class represents a vacation space from the game
 */

package assets;

public class VacationSpace extends Space {
    public VacationSpace() {
        this.description = "VACATION";
    }
    public int getTotalVacationTime() {
        return SpaceConstants.TOTAL_VACATION_TIME;
    }
}