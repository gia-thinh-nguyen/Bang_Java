package Cards.Equipments;

import Cards.LABEL;
import Cards.SUIT;

public class Barrel extends Equipment {
    public Barrel(LABEL label, SUIT suit){
        super(label, suit, "Barrel", EquipmentType.BARREL);
    }
    @Override
    public void played() {
        super.played();
    }
}
