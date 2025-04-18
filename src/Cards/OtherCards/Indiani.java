package Cards.OtherCards;

import Cards.Card;
import Cards.LABEL;
import Cards.SUIT;
import Game.ActionMenu;

public class Indiani extends Card {
    public Indiani(LABEL label, SUIT suit){
        super(label,suit,"Indiani");
    }
    @Override
    public void played(){
        super.played();
        ActionMenu.showIndianiMenu(getOwner());
    }
}
