package Players.Heros;

import Players.Player;
import Players.Role;
import Players.Character;
import Game.Game;
import Game.GameBoard;

public class SidKetchum extends Player {
    public SidKetchum(String name, Game game, GameBoard gameBoard, Role role) {
        super(name, game, gameBoard, role, Character.SID_KETCHUM);
    }

}
