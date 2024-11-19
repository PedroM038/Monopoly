package assets;

public class Space {
    protected String description;

    public Space() {
        
    }

    public Space emptySpace() {
        return new Space();
    }

    // GETTERS
    public String getDescription() {
        return this.description;
    }
}
