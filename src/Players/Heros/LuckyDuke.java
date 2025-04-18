package Players.Heros;

import Game.GameBoard;
import Players.Player;
import Players.Role;
import Players.Character;
import Game.Game;

public class LuckyDuke extends Player {
    public LuckyDuke(String name, Game game, GameBoard gameBoard, Role role) {
        super(name, game, gameBoard, role, Character.LUCKY_DUKE);
    }

}
