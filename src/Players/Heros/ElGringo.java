package Players.Heros;

import Cards.Card;
import Game.Game;
import Game.GameBoard;
import Players.Character;
import Players.Player;
import Players.Role;

public class ElGringo extends Player {
    public ElGringo(String name, Game game, GameBoard gameBoard, Role role) {
        super(name, game, gameBoard, role, Character.EL_GRINGO);
        setHealth(isSheriff() ? 4 : 3); // El Gringo starts with 3 health
        setMaxHealth(isSheriff() ? 4 : 3); // El Gringo's max health is 3
    }
    @Override
    public void takeDamage(Player source) {
        //El Gringo steal 1 card from the source when he takes damage.
        super.takeDamage(source);
        if(source==null) return;
        if(source.getHand().isEmpty()) return;
        if(getIsAlive()) {
            Card stolenCard = source.removeRandomCardFromHand();
            if(stolenCard != null) {
                addToHand(stolenCard);
                System.out.println(getName() + " stole " + stolenCard + " from " + source.getName());
            }
        }
    }

}
