package Cards.TargetCards;

import Cards.Card;
import Cards.LABEL;
import Cards.SUIT;
import Cards.TargetType;
import Game.ActionMenu;
import Players.Player;

public class Bang extends Card {
    public Bang(LABEL label, SUIT suit) {
        super(label, suit, "Bang");
    }
    @Override
    public void played() {
        super.played();
        Player target = ActionMenu.showTargetablePlayer(getOwner(), TargetType.BANG);
        ActionMenu.showRespondToBangMenu(getOwner(), target);
    }
}
