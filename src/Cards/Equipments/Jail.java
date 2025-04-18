package Cards.Equipments;

import Cards.Card;
import Cards.LABEL;
import Cards.SUIT;
import Cards.TargetType;
import Game.ActionMenu;
import Players.Player;

public class Jail extends Card {
    public Jail(LABEL label, SUIT suit) {
        super(label, suit, "Jail");
    }

    @Override
    public void played() {
        getOwner().removeFromHand(this);
        Player target = ActionMenu.showTargetablePlayer(getOwner(), TargetType.JAIL);
        target.addToEquipmentMap(EquipmentType.JAIL, this);
    }
}
