import javax.swing.SwingUtilities;
import view.GameUI;

public class Monopoly {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameUI());
    }    
}
