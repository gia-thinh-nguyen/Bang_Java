package Cards.Equipments;

import Cards.Card;
import Cards.LABEL;
import Cards.SUIT;
import Players.Player;

public class Dynamite extends Card {
    public Dynamite(LABEL label, SUIT suit) {
        super(label, suit, "Dynamite");
    }

    @Override
    public void played(Player target) {
        target.setDynamited(true);
    }
}
