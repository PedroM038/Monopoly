package assets;

public class PropertySpace extends Space {
    private Property property;
    private int ownerId;
    public PropertySpace(Property property) {
        this.description = property.getName();
        this.property = property;
        this.ownerId = -1;  // -1 means no owner 
    }

    public void buyProperty(int ownerId) {
        if (this.hasOwner()) {
            throw new ArithmeticException("Property already has owner.");
        }
        this.ownerId = ownerId;
        this.property.buy();
    }

    public void sellProperty() {
        if (!this.hasOwner()) {
            throw new ArithmeticException("Property does not have an owner.");
        }
        this.ownerId = -1;
        this.property.sell();
    }

    public boolean isOwner(int playerId) {
        return this.ownerId == playerId;
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