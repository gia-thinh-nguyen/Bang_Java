package Players.Heros;

import Game.GameBoard;
import Players.Player;
import Players.Role;
import Players.Character;
import Game.Game;

public class VultureSam extends Player {
    public VultureSam(String name, Game game, GameBoard gameBoard, Role role) {
        super(name, game, gameBoard, role, Character.VULTURE_SAM);
    }

}
