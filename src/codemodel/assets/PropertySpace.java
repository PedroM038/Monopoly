/*
 * The PropertySpace class represents a space with a property from the game
 */

package codemodel.assets;

public class PropertySpace extends Space {
    private Property property;

    public PropertySpace(Property property) {
        this.description = property.getName();
        this.property = property;
    }

    public Property getProperty() {
        return this.property;
    }
}