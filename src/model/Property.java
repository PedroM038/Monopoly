package model;

public class Property {
	private String name;
	private short[] values;
	private short level;
	private short ownerId;
    
	public Property(String name, int values[], int level) {
		this.name = name;
		this.values = values;
		this.level = level;
	}

        public short getValue(int level) {
            return this.value[level];
        }

        public String getName() {
            return this.name;
        }

        public short getLevel() {
            return this.level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

		public short getOwnerId() {
			return this.ownerId;
		}

		public void setOwnerId(short ownerId) {
			this.ownerId = ownerId;
		}
}
