package assets;

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