package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Space> spaces;

    public Board() {
        this.spaces = new ArrayList<>();
        initializeBoard();
    }

    /*
     Método para inicializar os espaços no tabuleiro
     Ainda falta implementação, pois precisamos definir
     a ordem dos espaços.
    */
    public void initializeBoard() {
        // Adicione os espaços aqui
    }

    public Space getSpace(int position) {
        if (position >= 0 && position < spaces.size()) {
            return spaces.get(position);
        } else {
            throw new IllegalArgumentException("Posição inválida no tabuleiro.");
        }
    }

    public int getNumberOfSpaces() {
        return spaces.size();
    }

    public void addSpace(Space space) {
        spaces.add(space);
    }
}
