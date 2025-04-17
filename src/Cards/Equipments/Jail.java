package Cards.Equipments;

import Cards.Card;
import Cards.LABEL;
import Cards.SUIT;
import Game.ActionMenu;
import Players.Player;

public class Jail extends Equipment {
    public Jail(LABEL label, SUIT suit) {
        super(label, suit, "Jail", EquipmentType.JAIL);
    }

    @Override
    public void played() {
        super.played();
        Player target = ActionMenu.showTargetablePlayer(getOwner().getPlayers(),getOwner().getGunRange());
        target.setJailed(true);
    }
}
