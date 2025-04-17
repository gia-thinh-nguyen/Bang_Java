package Cards.Equipments;

import Cards.Card;
import Cards.LABEL;
import Cards.SUIT;
public class Mustang extends Card{
    public Mustang(LABEL label, SUIT suit) {
        super(label, suit, "Mustang");
    }

    @Override
    public void played() {
        getOwner().setHorse(1);
        getOwner().addToEquipments(this);
    }

}
