package Cards.GunCards;

import Cards.Card;
import Cards.LABEL;
import Cards.SUIT;
import Players.Player;

public class GunCard extends Card {
    private final int range;

    public GunCard(LABEL label, SUIT suit, String name, int range) {
        super(label, suit, name);
        this.range = range;
    }
    @Override
    public void played() {
        getOwner().setGunRange(range);
    }
}
