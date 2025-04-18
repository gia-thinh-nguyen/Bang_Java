package Cards.OtherCards;

import Cards.Card;
import Cards.LABEL;
import Cards.SUIT;
import Game.ActionMenu;

public class Gatling extends Card {
    public Gatling(LABEL label, SUIT suit){
        super(label,suit,"Gatling");
    }
    @Override
    public void played(){
        super.played();
        ActionMenu.showGatlingMenu(getOwner());
    }
}
