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
    @Override
    public String toString(){
        String suitString = "";
        switch (suit){
            case SPADE -> suitString = "♠";
            case HEART -> suitString = "♥";
            case DIAMOND -> suitString = "♦";
            case CLUB -> suitString = "♣";
        }
        String labelString = "";
        switch (label){
            case ACE -> labelString = "A";
            case JACK -> labelString = "J";
            case QUEEN -> labelString = "Q";
            case KING -> labelString = "K";
            default -> labelString = String.valueOf(label.getValue());
        }
        return name + " " + labelString + suitString;
    }
    public void played(){
        owner.removeFromHand(this);
        owner.getGameBoard().Discard(this);
    }
    public void setOwner(Player owner){
        this.owner = owner;
    }
    public Player getOwner(){
        return owner;
    }
}
