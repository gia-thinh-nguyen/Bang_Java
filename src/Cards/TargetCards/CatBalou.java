package Cards.TargetCards;

import Cards.Card;
import Cards.LABEL;
import Cards.SUIT;
import Game.ActionMenu;
import Players.Player;
import Cards.TargetType;

public class CatBalou extends Card {
    public CatBalou(LABEL label, SUIT suit) {
        super(label, suit,"CatBalou");
    }
    @Override
    public void played(){
        super.played();
        Player target = ActionMenu.showTargetablePlayer(getOwner(), TargetType.CATBALOU);
        ActionMenu.showCatBalouMenu(target);
    }
}
