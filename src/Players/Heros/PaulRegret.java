package Players.Heros;

import Game.GameBoard;
import Game.Game;
import Players.Player;
import Players.Role;
import Players.Character;

public class PaulRegret extends Player {
    public PaulRegret(String name, Game game, GameBoard gameBoard, Role role) {
        super(name, game, gameBoard, role, Character.PAUL_REGRET);
        setHealth(isSheriff()?4:3); // Paul Regret starts with 3 health
        setMaxHealth(isSheriff()?4:3); // Paul Regret's max health is 3
    }
    @Override
    public int getDefenseHorse() {
        return super.getDefenseHorse() + 1; // Paul Regret has +1 defense horse
    }
}
