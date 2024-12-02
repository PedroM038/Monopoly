package assets;

import java.io.*;

public class Space implements Serializable {
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
