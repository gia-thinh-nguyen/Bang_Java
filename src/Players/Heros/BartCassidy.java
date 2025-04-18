package Players.Heros;

import Players.Player;
import Players.Role;
import Players.Character;
import Cards.Card;
import Game.*;


public class BartCassidy extends Player {
    public BartCassidy(String name, Game game, GameBoard gameBoard, Role role) {
        super(name, game, gameBoard, role, Character.BART_CASSIDY);
    }
    @Override
    public void takeDamage(Player damageSource) {
        // Call the parent class's takeDamage method
        super.takeDamage(damageSource);

        // Bart Cassidy's unique behavior: draw a card if still alive
        if (getIsAlive()) {
            System.out.println("Bart Cassidy loses 1 health and draws 1 card.");
            draw(1);
        }
    }
}
