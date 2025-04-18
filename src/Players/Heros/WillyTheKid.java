package Players.Heros;

import Game.Game;
import Game.GameBoard;
import Players.Character;
import Players.Player;
import Players.Role;

public class WillyTheKid extends Player {
    public WillyTheKid(String name, Game game, GameBoard gameBoard, Role role, Character character) {
        super(name, game, gameBoard, role, character);
        this.setBangUnlimited(true); // Set bangUnlimited to true
    }
    @Override
    public void setBangUnlimited(boolean bangUnlimited) {
        super.setBangUnlimited(true); // WillyTheKid always has unlimited bang
    }
}
