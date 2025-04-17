package Cards.Equipments;

import Cards.Card;
import Cards.LABEL;
import Cards.SUIT;
import Players.Player;

public class Barrel extends Card {
    public Barrel(LABEL label, SUIT suit){
        super(label, suit, "Barrel");
    }
    @Override
    public void played() {
        getOwner().setBarrel(true);
        getOwner().addToEquipments(this);
    }
}
