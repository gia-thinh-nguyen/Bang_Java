package Cards;

import Players.Player;
public abstract class Card {
    private SUIT suit;
    private LABEL label;
    private String name;
    private Player owner;
    public Card ( LABEL label,SUIT suit,String name){
        this.suit = suit;
        this.label = label;
        this.name = name;
    }
    public void played(){
        played(null);
    }
    public void played(Player target){
    }
    public void setOwner(Player owner){
        this.owner = owner;
    }
    public Player getOwner(){
        return owner;
    }
}
