/**
 * Space
 */
public class Space {
	private String name;
	private int type; //diferenciate luck spaces, property spaces and special spaces
    
	public Space(String name, int type) {
                this.name = name;
		this.type = type;
	}

        public String getName() {
                return this.name;
        }

        public int getType() {
                return this.type;
        }
}
