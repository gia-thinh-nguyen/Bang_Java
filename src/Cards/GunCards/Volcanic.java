package Cards.GunCards;

import Cards.LABEL;
import Cards.SUIT;
import Players.Player;
public class Volcanic extends GunCard {
    public Volcanic(LABEL label, SUIT suit) {
        super(label, suit, "Volcanic", 1);
    }

    @Override
    public void played() {
        super.played();
        getOwner().setBangUnlimited(true);
    }
}
