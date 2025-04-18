package Players.Heros;

import Cards.Card;
import Players.Player;
import Players.Role;
import Players.Character;
import Game.*;



public class PedroRamirez extends Player {
    public PedroRamirez(String name, Game game, GameBoard gameBoard, Role role) {
        super(name, game, gameBoard, role, Character.PEDRO_RAMIREZ);
    }

    @Override
    protected void firstPhaseDraw() {
        // Pedro Ramirez's first phase: may draw first card from the discard pile
        Card firstCard = ActionMenu.showPedroRamirezMenu(getGameBoard());
        if (firstCard != null) {
            addToHand(firstCard);
            firstCard.setOwner(this);
            System.out.println(getName() + " drew " + firstCard.getName() + " from the discard pile.");
        }
        // Draw the second card from the draw pile
        draw(1);
    }
}
