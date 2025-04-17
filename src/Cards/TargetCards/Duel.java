package Cards.TargetCards;

import Cards.Card;
import Cards.LABEL;
import Cards.SUIT;
import Cards.TargetType;
import Game.ActionMenu;
import Players.Player;

public class Duel extends Card {
    public Duel(LABEL label, SUIT suit) {
        super(label, suit, "Duel");
    }

    @Override
    public void played() {
        super.played();
        Player target = ActionMenu.showTargetablePlayer(getOwner(), TargetType.DUEL);
        ActionMenu.showDuelMenu(getOwner(), target);
    }
}
