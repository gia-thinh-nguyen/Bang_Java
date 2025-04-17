package Cards.Equipments;

import Cards.LABEL;
import Cards.SUIT;

public class Scope extends Equipment{
    public Scope(LABEL label, SUIT suit){
        super(label, suit, "Scope", EquipmentType.HORSE);
    }
    @Override
    public void played() {
        super.played();
        getOwner().setHorse(-1);
    }
}
