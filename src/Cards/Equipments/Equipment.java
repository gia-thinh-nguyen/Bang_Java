package Cards.Equipments;

import Cards.Card;
import Cards.LABEL;
import Cards.SUIT;

public abstract class Equipment extends Cards.Card {
    private EquipmentType equipmentType;
    public Equipment(LABEL label, SUIT suit, String name, EquipmentType equipmentType) {
        super(label, suit, name);
        this.equipmentType = equipmentType;
    }

    @Override
    public void played() {
        getOwner().removeFromHand(this,false);
        //cannot have duplicate equipments
        //if already have the same equipment type, discard the old one
        //keys initialized so always contains keys
        getOwner().removeEquipment(equipmentType);
        getOwner().addToEquipmentMap(equipmentType,this); //not for jail
    }

}
