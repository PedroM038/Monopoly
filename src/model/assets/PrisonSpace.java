package assets;

public class PrisonSpace extends Space {
    public PrisonSpace() {
        this.description = "PRISON";
        this.type = SpaceConstants.PRISON_SPACE_TYPE;
    }
    public int getTotalJailTime() {
        return SpaceConstants.TOTAL_JAIL_TIME;
    }
}