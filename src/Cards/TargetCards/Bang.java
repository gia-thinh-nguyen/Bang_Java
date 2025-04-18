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
        if(!getOwner().BangUnlimited()&&getOwner().AlreadyBanged()){
            System.out.println("Cannot Bang.");
            return;
        }
        super.played();
        Player target = ActionMenu.showTargetablePlayer(getOwner(), TargetType.BANG);
        ActionMenu.showRespondToBangMenu(getOwner(), target);
        getOwner().setAlreadyBanged(true);
    }
}
