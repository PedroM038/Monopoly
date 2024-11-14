package assets;

public class PropertySpace extends Space {
    private Property property;
    private int ownerId;
    public PropertySpace(Property property) {
        this.description = property.getName();
        this.type = SpaceConstants.PROPERTY_SPACE_TYPE;
        this.property = property;
        this.ownerId = -1;  // -1 means no owner 
    }

    public boolean hasOwner() {
        return this.ownerId != -1;
    }

    public Property getProperty() {
        return this.property;
    }

    public int getOwner() {
        return this.ownerId;
    }
}