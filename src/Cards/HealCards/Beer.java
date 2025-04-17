package Cards.HealCards;

import Cards.Card;
import Cards.LABEL;
import Cards.SUIT;
import Players.Player;
public class Beer extends Card {
    public Beer(LABEL label, SUIT suit) {
        super(label, suit, "Beer");
    }

    @Override
    public void played(){
        for(Player player: getOwner().getPlayers()){
            player.heal();
        }
    }
}
