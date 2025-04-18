package Players.Heros;

import Game.GameBoard;
import Players.Player;
import Players.Role;
import Players.Character;
import Game.Game;

public class RoseDoolan extends Player {
    public RoseDoolan(String name, Game game, GameBoard gameBoard, Role role) {
        super(name, game, gameBoard, role, Character.ROSE_DOOLAN);
    }

    @Override
    public int getAttackHorse() {
        return super.getAttackHorse() + 1; // Rose Doolan has +1 attack horse
    }
}
