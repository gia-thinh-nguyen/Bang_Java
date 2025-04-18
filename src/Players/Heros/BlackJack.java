package Players.Heros;

import Cards.Card;
import Cards.SUIT;
import Players.Player;
import Players.Role;
import Players.Character;
import Game.Game;
import Game.GameBoard;


public class BlackJack extends Player {
    public BlackJack(String name, Game game, GameBoard gameBoard, Role role) {
        super(name, game, gameBoard, role, Character.BLACK_JACK);
    }
    @Override
    protected void firstPhaseDraw(){
        //BlackJack reveals his second card. On heart or diamond, he draws a card.
        draw(1);
        Card secondCard= getGameBoard().DrawFromPile();
        secondCard.setOwner(this);
        addToHand(secondCard);
        SUIT suit = secondCard.getSuit();
        System.out.println("BlackJack's second card is: " + secondCard);
        if(suit== SUIT.HEART || suit== SUIT.DIAMOND) {
            System.out.println("BlackJack draws a card.");
            draw(1);
        }
    }
}
