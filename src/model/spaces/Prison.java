package spaces;

import java.util.ArrayList;

public class Prison extends Space {
    class Prison() {
        this.playersId = new ArrayList<int>();
    }

    public String action(Player player) {
        player.lock();
    }
}