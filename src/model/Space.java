package model;

public class Space {
	private String name;
	private short type; //diferenciate luck spaces, property spaces and special spaces
    
	public Space() {
	}

	public Space(String name, short type) {
        this.name = name;
		this.type = type;
	}

    public String getName() {
        return this.name;
    }

    public short getType() {
        return this.type;
    }

	abstract void action();
}
