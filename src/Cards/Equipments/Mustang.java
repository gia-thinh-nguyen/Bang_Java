package Cards.Equipments;

import Cards.Card;
import Cards.LABEL;
import Cards.SUIT;
public class Mustang extends Equipment{
    public Mustang(LABEL label, SUIT suit) {
        super(label, suit, "Mustang", EquipmentType.HORSE);
    }

    @Override
    public void played() {
        super.played();
        getOwner().setHorse(1);
    }

}
