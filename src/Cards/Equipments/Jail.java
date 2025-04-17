package Cards.Equipments;

import Cards.Card;
import Cards.LABEL;
import Cards.SUIT;
import Players.Player;

public class Jail extends Card {
    public Jail(LABEL label, SUIT suit) {
        super(label, suit, "Jail");
    }

    @Override
    public void played(Player target) {
        target.setJailed(true);
        target.addToEquipments(this);
    }
}
