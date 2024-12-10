package view;
import java.util.ArrayList;
import javax.swing.*;

public class MonopolySetup {
    private int numberOfPlayers;
    private int choice;
    private ArrayList<String> playerNames;
    private ArrayList<String> playerColors;
    private final String[] validColors = {"red", "blue", "purple", "black", "white", "green"};

    public MonopolySetup() {
        startGamePrompt();
    }

    private void askNumberOfPlayers() {
        boolean validInput = false;
        while (!validInput) {
            String input = JOptionPane.showInputDialog(null, "Digite o número de jogadores (2 a 6):", "Número de Jogadores", JOptionPane.QUESTION_MESSAGE);
            try {
                numberOfPlayers = Integer.parseInt(input);
                if (numberOfPlayers < 2 || numberOfPlayers > 6) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um número entre 2 e 6.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    validInput = true;
                    playerNames = new ArrayList<>();
                    playerColors = new ArrayList<>();
                    askPlayerDetails();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, insira um número entre 2 e 6.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void askPlayerDetails() {
        for (int i = 0; i < numberOfPlayers; i++) {
            String name = null;
            String color = null;
            boolean validColor = false;

            while (name == null || name.trim().isEmpty()) {
                name = JOptionPane.showInputDialog(null, "Digite o nome do jogador " + (i + 1) + ":", "Nome do Jogador", JOptionPane.QUESTION_MESSAGE);
                if (name == null || name.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nome não pode ser vazio. Por favor, insira um nome válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }

            while (!validColor) {
                color = JOptionPane.showInputDialog(null, "Digite a cor do boneco do jogador " + (i + 1) + ":", "Cor do Boneco", JOptionPane.QUESTION_MESSAGE);
                if (color != null && isValidColor(color.trim().toLowerCase())) {
                    validColor = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Cor inválida. Por favor, insira uma das seguintes cores: red, blue, purple, black, white, green.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }

            playerNames.add(name);
            playerColors.add(color);
        }
        JOptionPane.showMessageDialog(null, "Configuração concluída!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean isValidColor(String color) {
        for (String validColor : validColors) {
            if (validColor.equals(color)) {
                return true;
            }
        }
        return false;
    }

    private void startGamePrompt() {
        Object[] options = {"Novo Jogo", "Continuar Jogo"};
        int choice = JOptionPane.showOptionDialog(null,
                "Selecione uma opção:",
                "Monopoly",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (choice == JOptionPane.YES_OPTION) {
            this.choice = 1;
            askNumberOfPlayers();
        } else {
            this.choice = 0;
        }
    }

    public ArrayList<String> getPlayerNames() {
        return this.playerNames;
    }

    public ArrayList<String> getPlayerColors() {
        return this.playerColors;
    }

    public int getNumberOfPlayers() {
        return this.numberOfPlayers;
    }

    public int getChoice() {
        return this.choice;
    }
}