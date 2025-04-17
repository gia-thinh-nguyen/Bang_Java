package Cards.DrawCards;

import Cards.Card;
import Cards.LABEL;
import Cards.SUIT;
import Players.Player;

public class Stagecoach extends Card {
    public Stagecoach(LABEL label, SUIT suit) {
        super(label,suit, "Stagecoach");
    }
    @Override
    public void played() {
        getOwner().draw(2);
    }
}
