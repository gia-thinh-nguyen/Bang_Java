package Cards.HealCards;

import Cards.Card;
import Cards.LABEL;
import Cards.SUIT;
import Players.Player;

public class Saloon extends Card {
    public Saloon(LABEL label, SUIT suit) {
        super(label, suit, "Saloon");
    }

    @Override
    public void played() {
        super.played();
       //heal all players
        for (Player player : getOwner().getPlayers()) {
            player.heal();
        }
    }
}
