package spaces;

public class Space {
    private String name;
    private String description;
    private short type; //diferenciate luck spaces, property spaces and special spaces
    
    public Space() {
    }

    public Space(String name, String description, short type) {
        this.name = name;
        this.description = description;
	this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    public short getType() {
        return this.type;
    }

    public void printSpace() {
        System.out.println(this.getName);
        System.out.println(this.getDescription);

        if (this.type == 2) //chance type
            action(chance, player);
        if (this.type == 3) //prision type
            action(player)
    }

    abstract void action();
}
