package Cards.OtherCards;

import Cards.Card;
import Cards.LABEL;
import Cards.SUIT;
import Cards.TargetType;
import Game.ActionMenu;
import Players.Character;
import Players.Player;

public class Missed extends Card {
    public Missed(LABEL label, SUIT suit) {
        super(label, suit, "Missed");
    }

    @Override
    public void played() {
        // Missed can be played only by Calamity Janet as Bang
        if(getOwner().getCharacter()!= Character.CALAMITY_JANET){
            System.out.println("only Calamity Janet can play this card");
            return;
        }
        if(!getOwner().BangUnlimited()&&getOwner().AlreadyBanged()){
            System.out.println("Already played Bang this turn.");
            return;
        }
        super.played();
        Player target = ActionMenu.showTargetablePlayer(getOwner(), TargetType.BANG);
        ActionMenu.showRespondToBangMenu(getOwner(), target);
        getOwner().setAlreadyBanged(true);
    }
}
