package Cards.Equipments;

import Cards.Card;
import Cards.LABEL;
import Cards.SUIT;

public class Equipment extends Cards.Card {
    private EquipmentType equipmentType;
    public Equipment(LABEL label, SUIT suit, String name, EquipmentType equipmentType) {
        super(label, suit, name);
        this.equipmentType = equipmentType;
    }

    @Override
    public void played() {
        getOwner().removeFromHand(this);
        //cannot have duplicate equipments
        //if already have the same equipment type, discard the old one
        //keys initialized so always contains keys
        Card oldEquipment = getOwner().getEquipmentMap().get(equipmentType);
        if(oldEquipment != null){
            getOwner().getGameBoard().Discard(oldEquipment);
        }
        getOwner().addToEquipmentMap(equipmentType,this);
    }

}
