package Cards.HealCards;

import Cards.Card;
import Cards.LABEL;
import Cards.SUIT;

public class Saloon extends Card {
    public Saloon(LABEL label, SUIT suit) {
        super(label, suit, "Saloon");
    }

    @Override
    public void played() {
       //heal all players
    }
}
