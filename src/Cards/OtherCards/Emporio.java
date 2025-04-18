package Cards.OtherCards;

import Cards.Card;
import Cards.LABEL;
import Cards.SUIT;
import Game.ActionMenu;

public class Emporio extends Card {
    public Emporio(LABEL label, SUIT suit) {
        super(label, suit, "Emporio");
    }

    @Override
    public void played() {
        super.played();
        ActionMenu.showEmporioMenu(getOwner());
    }
}
