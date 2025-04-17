package Cards.OtherCards;

import Cards.Card;
import Cards.LABEL;
import Cards.SUIT;

public class Missed extends Card {
    public Missed(LABEL label, SUIT suit) {
        super(label, suit, "Missed");
    }

    @Override
    public void played() {
        super.played();
    }
}
