package Players.Heros;

import Game.GameBoard;
import Game.Game;
import Players.Player;
import Players.Role;
import Players.Character;

public class Jourdonnais extends Player {
    public Jourdonnais(String name, Game game, GameBoard gameBoard, Role role) {
        super(name, game, gameBoard, role, Character.JOURDONNAIS);
    }


}
