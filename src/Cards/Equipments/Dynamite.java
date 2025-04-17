package Cards.Equipments;

import Cards.LABEL;
import Cards.SUIT;
import Game.ActionMenu;
import Players.Player;

public class Dynamite extends Equipment {
    public Dynamite(LABEL label, SUIT suit) {
        super(label, suit, "Dynamite", EquipmentType.DYNAMITE);
    }

    @Override
    public void played() {
        super.played();
        getOwner().setDynamited(true);
    }
}
