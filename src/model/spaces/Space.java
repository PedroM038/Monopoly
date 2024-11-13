package spaces;

public class Space {
    protected String description;
    protected int type; //diferenciate luck spaces, property spaces and special spaces

    public String getDescription() {
        return this.description;
    }

    public short getType() {
        return this.type;
    }

    abstract String action(Player player);
}
