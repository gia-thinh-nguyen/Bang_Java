package Cards.TargetCards;

import Cards.Card;
import Cards.LABEL;
import Cards.SUIT;
import Cards.TargetType;
import Game.ActionMenu;
import Players.Player;
import Players.Character;

public class Bang extends Card {
    public Bang(LABEL label, SUIT suit) {
        super(label, suit, "Bang");
    }
    @Override
    public void played() {
        if(!getOwner().BangUnlimited()&&getOwner().AlreadyBanged()){
            System.out.println("Already played Bang this turn.");
            return;
        }
        super.played();
        Player target = ActionMenu.showTargetablePlayer(getOwner(), TargetType.BANG);
        boolean isSlabTheKiller = getOwner().getCharacter()== Character.SLAB_THE_KILLER;
        if (isSlabTheKiller){
            ActionMenu.showRespondToSTKBang(getOwner(), target);
        }
        else{
            ActionMenu.showRespondToBangMenu(getOwner(),target);
        }
        getOwner().setAlreadyBanged(true);
    }
}
