package Players.Heros;

import Cards.Card;
import Game.GameBoard;
import Players.Player;
import Players.Role;
import Players.Character;
import Game.Game;

public class SuzyLafayette extends Player {
    public SuzyLafayette(String name, Game game, GameBoard gameBoard, Role role) {
        super(name, game, gameBoard, role, Character.SUZY_LAFAYETTE);
    }
    //Suzy Lafayette draw a card when her hand is empty
    @Override
    public void removeFromHand(Card card, boolean isInDiscardPhase) {
        super.removeFromHand(card,isInDiscardPhase);
        if (!isInDiscardPhase && getHand().isEmpty() && getIsAlive()) {
            System.out.println("Suzy Lafayette has no cards in hand, drawing a card.");
            draw(1);
        }
    }
    @Override
    public void discardFirstCardFromHand(String cardName) {
        super.discardFirstCardFromHand(cardName);
        if (getHand().isEmpty() && getIsAlive()) {
            System.out.println("Suzy Lafayette has no cards in hand, drawing a card.");
            draw(1);
        }
    }
    @Override
    public Card removeRandomCardFromHand() {
        Card removedCard = super.removeRandomCardFromHand();
        if (getHand().isEmpty() && getIsAlive()) {
            System.out.println("Suzy Lafayette has no cards in hand, drawing a card.");
            draw(1);
        }
        return removedCard;
    }


}
