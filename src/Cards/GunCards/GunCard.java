package Cards.GunCards;

import Cards.Card;
import Cards.Equipments.Equipment;
import Cards.Equipments.EquipmentType;
import Cards.LABEL;
import Cards.SUIT;
import Players.Player;

public class GunCard extends Equipment {
    private final int range;

    public GunCard(LABEL label, SUIT suit, String name, int range) {
        super(label, suit, name, EquipmentType.GUN);
        this.range = range;
    }
    @Override
    public void played() {
        super.played();
        getOwner().setGunRange(range);
    }
}
