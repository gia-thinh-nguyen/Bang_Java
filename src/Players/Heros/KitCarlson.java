package Players.Heros;

import Cards.Card;
import Game.Game;
import Game.GameBoard;
import Players.Character;
import Players.Player;
import Players.Role;
import Game.ActionMenu;

public class KitCarlson extends Player {
    public KitCarlson(String name, Game game, GameBoard gameBoard, Role role) {
        super(name, game, gameBoard, role, Character.KIT_CARLSON);
    }

    //Kit Carlson's first phase: choose 2 of 3 top cards from the draw pile
    @Override
    protected void firstPhaseDraw() {
        Card[] drawnCards = new Card[3];
        for (int i = 0; i < 3; i++) {
            drawnCards[i] = getGameBoard().DrawFromPile();
        }
        Card putbackCard = ActionMenu.showKitCarlsonMenu(drawnCards);
        for(Card card : drawnCards){
            if(card == putbackCard){
                getGameBoard().PutCardBack(card);
            }
            else{
                card.setOwner(this);
                addToHand(card);
            }
        }

    }
}
