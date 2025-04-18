package Players.Heros;

import Players.Player;
import Players.Role;
import Players.Character;
import Cards.Card;
import Game.Game;
import Game.GameBoard;
import Game.ActionMenu;

public class JesseJones extends Player {
    public JesseJones(String name, Game game, GameBoard gameBoard, Role role) {
        super(name, game, gameBoard, role, Character.JESSE_JONES);
    }
    @Override
    protected void firstPhaseDraw() {
        // Jesse Jones may draw his first card from another player's hand
        Player target = ActionMenu.showJesseJonesMenu(this);
        if(target==null){
            draw(2);
        }
        else{
            Card stolenCard = target.removeRandomCardFromHand();
            addToHand(stolenCard);
            draw(1);
        }


    }
}
