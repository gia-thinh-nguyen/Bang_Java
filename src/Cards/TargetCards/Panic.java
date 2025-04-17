package Cards.TargetCards;

import Cards.Card;
import Cards.LABEL;
import Cards.SUIT;
import Cards.TargetType;
import Game.ActionMenu;
import Players.Player;

public class Panic extends Card {
    public Panic(LABEL label, SUIT suit) {
        super(label, suit, "Panic");
    }

    @Override
    public void played(){
        super.played();
        Player target = ActionMenu.showTargetablePlayer(getOwner(), TargetType.PANIC);
        //get a random card from the target's hand then add to owner's hand
        Card stolenCard = target.removeRandomCardFromHand();
        if(stolenCard != null) {
            getOwner().addToHand(stolenCard);
            System.out.println(getOwner().getName() + " stole " + stolenCard + " from " + target.getName());
        }
    }

}
