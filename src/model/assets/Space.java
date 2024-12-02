/*
 * The Space class represents a space from the board of the game
 * It represents a generic space that's stores the data from that space
 * The focus of this class is to only store data, and doesn't act on other classes
 */

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
