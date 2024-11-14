package assets;

public class VacationSpace extends Space {
    public VacationSpace() {
        this.description = "VACATION";
        this.type = SpaceConstants.VACATION_SPACE_TYPE;
    }
    public int getTotalVacationTime() {
        return SpaceConstants.TOTAL_VACATION_TIME;
    }
}