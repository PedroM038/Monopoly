package view;
import java.util.ArrayList;
import javax.swing.*;

public class MonopolySetup {
    private int numberOfPlayers;
    private int choice;
    private ArrayList<String> playerNames;
    private ArrayList<String> playerColors;

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
            String name = JOptionPane.showInputDialog(null, "Digite o nome do jogador " + (i + 1) + ":", "Nome do Jogador", JOptionPane.QUESTION_MESSAGE);
            String color = JOptionPane.showInputDialog(null, "Digite a cor do boneco do jogador " + (i + 1) + ":", "Cor do Boneco", JOptionPane.QUESTION_MESSAGE);
            playerNames.add(name);
            playerColors.add(color);
        }
        JOptionPane.showMessageDialog(null, "Configuração concluída!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
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
        }
        else {
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