enum SUIT{
    HEART,DIAMOND,CLUB,SPADE
}
enum LABEL{
    ACE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,TEN,JACK,QUEEN,KING
}
public abstract class Card {
    public SUIT suit;
    public LABEL label;
    public String name;
    public Card (SUIT suit, LABEL label,String name){
        this.suit = suit;
        this.label = label;
        this.name = name;
    }
    public void played(Player source, Player target){
    }
}
