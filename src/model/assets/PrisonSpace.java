package assets;


public class PrisonSpace extends Space {
    public PrisonSpace() {
        this.description = "PRISON";
    }
    public int getTotalJailTime() {
        return SpaceConstants.TOTAL_JAIL_TIME;
    }
}