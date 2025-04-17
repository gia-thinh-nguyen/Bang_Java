package Cards.DrawCards;

import Cards.Card;
import Cards.LABEL;
import Cards.SUIT;
import Players.Player;

public class WellsFargo extends Card {
    public WellsFargo(LABEL label, SUIT suit) {
        super(label, suit, "Wells Fargo");
    }
    @Override
    public void played() {
        super.played();
        getOwner().draw(3);
    }
}
